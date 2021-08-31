package com.eddiej.apisearch.feature.bookdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eddiej.apisearch.databinding.FragmentBookDetailBinding
import com.eddiej.apisearch.feature.BaseFragment
import com.eddiej.apisearch.global.ProjectLayout
import com.eddiej.apisearch.global.ProjectString
import com.eddiej.apisearch.model.data.Book

class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>() {

    companion object {
        private const val PARAM_ITEM_KEY = "item"

        fun newInstance(item: Book): BookDetailFragment {
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
            val item = it.getParcelable<Book>(PARAM_ITEM_KEY)
            binding.item = item
        }
    }

    override fun getLayoutResourceId(): Int = ProjectLayout.fragment_book_detail
}