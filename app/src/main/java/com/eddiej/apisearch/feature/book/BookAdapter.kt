package com.eddiej.apisearch.feature.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eddiej.apisearch.databinding.BookListItemBinding
import com.eddiej.apisearch.domain.data.BookItemEntity

class BookAdapter(private val viewModel: BookViewModel) :
    PagingDataAdapter<BookItemEntity, BookAdapter.BookItemViewHolder>(BookItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = BookListItemBinding.inflate(inflator, parent, false)

        return BookItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookAdapter.BookItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(viewModel, it)
        }
    }

    class BookItemViewHolder constructor(private val binding: BookListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: BookViewModel, item: BookItemEntity) {
            binding.viewModel = viewModel
            binding.item = item
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                viewModel.moveToDetail(item)
            }
        }
    }
}

class BookItemDiffCallback : DiffUtil.ItemCallback<BookItemEntity>() {
    override fun areItemsTheSame(oldItem: BookItemEntity, newItem: BookItemEntity): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: BookItemEntity, newItem: BookItemEntity): Boolean {
        return oldItem == newItem
    }
}