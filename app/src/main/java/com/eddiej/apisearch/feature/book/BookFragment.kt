package com.eddiej.apisearch.feature.book

import androidx.fragment.app.viewModels
import com.eddiej.apisearch.databinding.FragmentBookBinding
import com.eddiej.apisearch.feature.BaseFragment
import com.eddiej.apisearch.global.ProjectLayout
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class BookFragment : BaseFragment<FragmentBookBinding>() {

    private val viewModel by viewModels<BookViewModel>()
    private lateinit var adapter: BookAdapter

    override fun setupViews() {
        binding.viewModel = viewModel

        adapter = BookAdapter(viewModel)
        binding.recyclerView.adapter = adapter
    }

    override fun bindViews() {
        binding.searchView.queryTextChanges()
            // 입력 후 1초 경과 시 Query 전달
            .debounce(1000, TimeUnit.MILLISECONDS, Schedulers.io())
            .map { charSequence -> charSequence.toString() }
            // 빈 문자열이나 공백은 무시
            .filter { query -> !query.isNullOrBlank() }
            .subscribe { query ->
                viewModel.getList(query)
                    .subscribe {
                        adapter.submitData(lifecycle, it)
                    }
                    .addTo(disposable)
            }
    }

    override fun getLayoutResourceId(): Int = ProjectLayout.fragment_book
}