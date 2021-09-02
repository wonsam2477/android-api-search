package com.eddiej.apisearch.feature.book

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.eddiej.apisearch.databinding.FragmentBookBinding
import com.eddiej.apisearch.feature.BaseFragment
import com.eddiej.apisearch.feature.bookdetail.BookDetailFragment
import com.eddiej.apisearch.global.ProjectId
import com.eddiej.apisearch.global.ProjectLayout
import com.eddiej.apisearch.util.FragmentUtils
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class BookFragment : BaseFragment<FragmentBookBinding>() {

    companion object {
        fun newInstance() = BookFragment()
    }

    private val viewModel by viewModels<BookViewModel>()
    private lateinit var bookAdapter: BookAdapter

    override fun setupViews() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // 페이징 어댑터
        bookAdapter = BookAdapter(viewModel)
        val loadingAdapter = LoadingStateAdapter {
            bookAdapter.retry()
        }
        bookAdapter.addLoadStateListener { loadStates ->
            loadingAdapter.loadState = loadStates.append
        }
        val concatAdapter = ConcatAdapter(bookAdapter, loadingAdapter)
        binding.recyclerView.adapter = concatAdapter

        binding.btnRefresh.setOnClickListener { bookAdapter.refresh() }
        binding.searchView.queryTextChanges()
            // 입력 후 1초 경과 시 Query 전달
            .debounce(1000, TimeUnit.MILLISECONDS, Schedulers.io())
            .map { charSequence -> charSequence.toString() }
            // 빈 문자열이나 공백은 무시
            .filter { query -> !query.isNullOrBlank() }
            .subscribe { query ->
                viewModel.getList(query)
                    .subscribe {
                        bookAdapter.submitData(lifecycle, it)
                    }
                    .addTo(disposable)

            }

        // 리스트 내에서 선택한 아이템의 데이터 수신
        viewModel.selectedItem.observe(this, { item ->
            val fragment = BookDetailFragment.newInstance(item)
            FragmentUtils.addFragment(parentFragmentManager, this, fragment, ProjectId.container)
        })
    }

    override fun getLayoutResourceId(): Int = ProjectLayout.fragment_book
}