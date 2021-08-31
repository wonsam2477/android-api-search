package com.eddiej.apisearch.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class BookItemModel(
    @SerializedName("author")
    val author: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("discount")
    val discount: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("isbn")
    val isbn: String = "",
    @SerializedName("link")
    val link: String = "",
    @SerializedName("price")
    val price: String = "",
    @SerializedName("pubdate")
    val pubdate: String = "",
    @SerializedName("publisher")
    val publisher: String = "",
    @SerializedName("title")
    val title: String = ""
) : Parcelable