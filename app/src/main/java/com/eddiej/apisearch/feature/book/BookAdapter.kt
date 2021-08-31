package com.eddiej.apisearch.feature.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eddiej.apisearch.databinding.BookListItemBinding
import com.eddiej.apisearch.model.data.Book

class BookAdapter(private val viewModel: BookViewModel) :
    PagingDataAdapter<Book, BookAdapter.BookItemViewHolder>(BookItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = BookListItemBinding.inflate(inflator, parent, false)
        return BookItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(viewModel, it)
        }
    }

    inner class BookItemViewHolder constructor(private val binding: BookListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: BookViewModel, item: Book) {
            binding.viewModel = viewModel
            binding.item = item
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                // TODO: 2021-08-30 아이템 클릭 시 액션
            }
        }
    }
}

class BookItemDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}