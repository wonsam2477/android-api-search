package com.eddiej.apisearch.feature.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eddiej.apisearch.databinding.BookListHeaderBinding
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class BookHeaderAdapter(private val viewModel: BookViewModel) :
    RecyclerView.Adapter<BookHeaderAdapter.BookHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHeaderViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = BookListHeaderBinding.inflate(inflator, parent, false)

        return BookHeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookHeaderViewHolder, position: Int) {
        holder.bind(viewModel)
    }

    override fun getItemCount(): Int = 1

    class BookHeaderViewHolder constructor(private val binding: BookListHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: BookViewModel) {
            binding.viewModel = viewModel

            binding.searchView.queryTextChanges()
                // 입력 후 1초 경과 시 Query 전달
                .debounce(1000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map { charSequence -> charSequence.toString() }
                // 빈 문자열이나 공백은 무시
                .filter { query -> !query.isNullOrBlank() }
                .subscribe { query ->
                    viewModel.pushQuery(query)
                }
        }
    }

}