package com.eddiej.apisearch.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookItemEntity(
    val author: String,
    val description: String,
    val discount: String,
    val image: String,
    val isbn: String,
    val link: String,
    val price: String,
    var pubdate: String,
    val publisher: String,
    val title: String
) : Parcelable