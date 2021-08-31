package com.eddiej.apisearch.model.data

import com.eddiej.apisearch.domain.data.BookEntity
import com.eddiej.apisearch.domain.data.BookItemEntity
import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

data class BookModel(
    @SerializedName("display")
    val display: Int = 0,
    @SerializedName("items")
    val items: List<BookItemModel> = listOf(),
    @SerializedName("lastBuildDate")
    val lastBuildDate: String = "",
    @SerializedName("start")
    val start: Int = 0,
    @SerializedName("total")
    val total: Int = 0
)

fun BookModel.toMap() = BookEntity(display, items.toMap(), lastBuildDate, start, total)

fun List<BookItemModel>.toMap(): List<BookItemEntity> {
    return map {
        // 날짜형식 년월일 표시
        val curDateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val newDateFormat = SimpleDateFormat("yyyy년MM월dd일", Locale.getDefault())
        val date = curDateFormat.parse(it.pubdate)
        val newPublishDate = newDateFormat.format(date)

        // 숫자형식 콤마표시
        val decimalFormat = DecimalFormat("#,###")
        val price =
            if (it.price.isNotBlank()) decimalFormat.format(it.price.toIntOrNull())
            else ""

        val discountPrice =
            if (it.discount.isNotBlank()) decimalFormat.format(it.discount.toIntOrNull())
            else ""


        // 태그형식 제거
        val regex = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>".toRegex()
        val title = it.title.replace(regex, "")
        val author = it.author.replace(regex, "")
        val description = it.description.replace(regex, "")
        val publisher = it.publisher.replace(regex, "")

        BookItemEntity(
            author,
            description,
            discountPrice,
            it.image,
            it.isbn,
            it.link,
            price,
            newPublishDate,
            publisher,
            title
        )
    }
}