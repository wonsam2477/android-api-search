package com.eddiej.apisearch.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.eddiej.apisearch.global.AndroidDrawable
import com.eddiej.apisearch.global.ProjectDrawable
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl")
fun loadImage(imageView: AppCompatImageView, url: String?) {
    if (url == null) return

    Picasso.get()
        .load(url)
        .placeholder(AndroidDrawable.ic_menu_report_image)
        .stableKey(url)
        .into(imageView)
}