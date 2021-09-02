package com.eddiej.apisearch.feature.book

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.eddiej.apisearch.databinding.FragmentBookBinding
import com.eddiej.apisearch.feature.BaseFragment
import com.eddiej.apisearch.feature.bookdetail.BookDetailFragment
import com.eddiej.apisearch.global.ProjectId
import com.eddiej.apisearch.global.ProjectLayout
import com.eddiej.apisearch.util.FragmentUtils
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.kotlin.addTo

@AndroidEntryPoint
class BookFragment : BaseFragment<FragmentBookBinding>() {

    companion object {
        fun newInstance() = BookFragment()
    }

    private val viewModel by viewModels<BookViewModel>()
    private lateinit var bookAdapter: BookAdapter

    override fun setupViews() {
        binding.viewModel = viewModel

        // 입력헤더 어댑터
        val headerAdapter = BookHeaderAdapter(viewModel)
        // 페이징 어댑터
        bookAdapter = BookAdapter(viewModel)
        bookAdapter.withLoadStateFooter(LoadingStateAdapter(bookAdapter))

        // 2개를 묶어서 같이 쓰자
        val concatAdapter = ConcatAdapter(headerAdapter, bookAdapter)
        binding.recyclerView.adapter = concatAdapter

        // 어댑터 내에서 쿼리결과를 받아와 데이터를 요청
        viewModel.queryText.observe(this, { query ->
            viewModel.getList(query)
                .subscribe {
                    bookAdapter.submitData(lifecycle, it)
                }
                .addTo(disposable)
        })

        // 리스트 내에서 선택한 아이템의 데이터 수신
        viewModel.selectedItem.observe(this, { item ->
            val fragment = BookDetailFragment.newInstance(item)
            FragmentUtils.addFragment(parentFragmentManager, this, fragment, ProjectId.container)
        })
    }

    override fun getLayoutResourceId(): Int = ProjectLayout.fragment_book
}