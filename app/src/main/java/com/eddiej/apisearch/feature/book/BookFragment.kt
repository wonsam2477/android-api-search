package com.eddiej.apisearch.feature.book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.eddiej.apisearch.R
import com.eddiej.apisearch.databinding.FragmentBookBinding
import com.eddiej.apisearch.feature.BaseFragment
import com.eddiej.apisearch.global.ProjectLayout

class BookFragment : BaseFragment<FragmentBookBinding>() {

    private val viewModel by viewModels<BookViewModel>()
    private lateinit var adapter: BookAdapter

    override fun setupViews() {
        binding.viewModel = viewModel

        adapter = BookAdapter(viewModel)
        binding.recyclerView.adapter = adapter

        viewModel.bookList.observe(this, { result ->
            adapter.submitList(result)
        })

    }

    override fun bindViews() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.getList(query)
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun getLayoutResourceId(): Int = ProjectLayout.fragment_book
}