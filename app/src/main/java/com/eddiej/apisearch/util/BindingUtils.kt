package com.eddiej.apisearch.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.eddiej.apisearch.global.ProjectDrawable
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl")
fun loadImage(imageView: AppCompatImageView, url: String?) {
    if (url.isNullOrBlank()) return

    Picasso.get()
        .load(url)
        .placeholder(ProjectDrawable.ic_baseline_image)
        .error(ProjectDrawable.ic_baseline_broken_image)
        .into(imageView)
}