package com.eddiej.apisearch.feature.book

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.eddiej.apisearch.databinding.FragmentBookBinding
import com.eddiej.apisearch.feature.BaseFragment
import com.eddiej.apisearch.global.ProjectLayout
import io.reactivex.rxjava3.kotlin.addTo

class BookFragment : BaseFragment<FragmentBookBinding>() {

    private val viewModel by viewModels<BookViewModel>()
    private lateinit var adapter: BookAdapter

    override fun setupViews() {
        setHasOptionsMenu(true)

        binding.viewModel = viewModel

        val headerAdapter = BookHeaderAdapter(viewModel)
        adapter = BookAdapter(viewModel)

        val concatAdapter = ConcatAdapter(headerAdapter, adapter)
        binding.recyclerView.adapter = concatAdapter

        viewModel.queryText.observe(this, { query ->
            viewModel.getList(query)
                .subscribe {
                    adapter.submitData(lifecycle, it)
                }
                .addTo(disposable)
        })
    }

    override fun getLayoutResourceId(): Int = ProjectLayout.fragment_book
}