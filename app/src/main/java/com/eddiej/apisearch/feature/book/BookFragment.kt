package com.eddiej.apisearch.feature.book

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.eddiej.apisearch.databinding.FragmentBookBinding
import com.eddiej.apisearch.feature.BaseFragment
import com.eddiej.apisearch.feature.bookdetail.BookDetailFragment
import com.eddiej.apisearch.global.ProjectId
import com.eddiej.apisearch.global.ProjectLayout
import com.eddiej.apisearch.util.FragmentUtils
import io.reactivex.rxjava3.kotlin.addTo

class BookFragment : BaseFragment<FragmentBookBinding>() {

    companion object {
        fun newInstance() = BookFragment()
    }

    private val viewModel by viewModels<BookViewModel>()
    private lateinit var adapter: BookAdapter

    override fun setupViews() {
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

        viewModel.selectedItem.observe(this, { item ->
            val fragment = BookDetailFragment.newInstance(item)
            FragmentUtils.addFragment(parentFragmentManager, this, fragment, ProjectId.container)
        })
    }

    override fun getLayoutResourceId(): Int = ProjectLayout.fragment_book
}