package com.eddiej.apisearch.model.data

import com.google.gson.annotations.SerializedName


data class BookList(
    @SerializedName("display")
    val display: Int?,
    @SerializedName("items")
    val items: List<Book>?,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String?,
    @SerializedName("start")
    val start: Int?,
    @SerializedName("total")
    val total: Int?
)

data class Book(
    @SerializedName("author")
    val author: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("discount")
    val discount: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("isbn")
    val isbn: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("pubdate")
    val pubdate: String?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("title")
    val title: String?
)