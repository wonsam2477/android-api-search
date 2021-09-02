package com.eddiej.apisearch.feature.bookdetail

import android.graphics.Paint
import android.os.Bundle
import com.eddiej.apisearch.databinding.FragmentBookDetailBinding
import com.eddiej.apisearch.domain.data.BookItemEntity
import com.eddiej.apisearch.feature.BaseFragment
import com.eddiej.apisearch.global.ProjectLayout
import com.eddiej.apisearch.model.data.BookItemModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>() {

    companion object {
        private const val PARAM_ITEM_KEY = "item"

        fun newInstance(item: BookItemEntity): BookDetailFragment {
            val fragment = BookDetailFragment()
            val bundle = Bundle().apply {
                putParcelable(PARAM_ITEM_KEY, item)
            }
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun setupViews() {
        arguments?.let {
            val item = it.getParcelable<BookItemEntity>(PARAM_ITEM_KEY)
            binding.item = item
        }

        with(binding.layoutContent.textDiscountPrice) {
            paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    override fun getLayoutResourceId(): Int = ProjectLayout.fragment_book_detail
}