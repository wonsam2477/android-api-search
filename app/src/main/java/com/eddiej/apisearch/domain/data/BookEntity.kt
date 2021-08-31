package com.eddiej.apisearch.domain.data

data class BookEntity(
    val display: Int,
    val items: List<BookItemEntity>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)