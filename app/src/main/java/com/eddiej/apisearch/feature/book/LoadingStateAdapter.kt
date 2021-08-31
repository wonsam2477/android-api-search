package com.eddiej.apisearch.feature.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eddiej.apisearch.databinding.LayoutLoadingBinding

class LoadingStateAdapter(private val adapter: BookAdapter) :
    LoadStateAdapter<LoadingStateAdapter.LoadingItemViewHolder>() {
    override fun onBindViewHolder(holder: LoadingItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingItemViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = LayoutLoadingBinding.inflate(inflator, parent, false)

        return LoadingItemViewHolder(binding)
    }

    class LoadingItemViewHolder(private val binding: LayoutLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                textMessage.isVisible = loadState is LoadState.Loading
            }
        }
    }
}