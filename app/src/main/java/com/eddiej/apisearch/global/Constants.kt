package com.eddiej.apisearch.global

import com.eddiej.apisearch.R

typealias AndroidColor = android.R.color
typealias AndroidDrawable = android.R.drawable
typealias AndroidString = android.R.string
typealias AndroidStyle = android.R.style
typealias AndroidDimen = android.R.dimen
typealias AndroidLayout = android.R.layout
typealias AndroidId = android.R.id

typealias ProjectColor = R.color
typealias ProjectDrawable = R.drawable
typealias ProjectString = R.string
typealias ProjectStyle = R.style
typealias ProjectDimen = R.dimen
typealias ProjectLayout = R.layout
typealias ProjectId = R.id

object Constants {
    private const val NAVER_API_ENDPOINT = "https://openapi.naver.com/v1/"

    const val NAVER_API_SEARCH = NAVER_API_ENDPOINT + "search/"
}