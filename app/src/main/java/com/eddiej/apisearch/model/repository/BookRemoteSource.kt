package com.eddiej.apisearch.model.repository

import com.eddiej.apisearch.global.Constants
import com.eddiej.apisearch.model.data.BookList
import com.eddiej.apisearch.model.network.RetrofitClient
import com.eddiej.apisearch.model.network.interceptor.NaverAuthInterceptor
import com.eddiej.apisearch.model.network.service.BookService
import io.reactivex.rxjava3.core.Single


class BookRemoteSource() {
    private val service =
        RetrofitClient.get(Constants.NAVER_API_SEARCH, listOf(NaverAuthInterceptor()))
            .create(BookService::class.java)

    fun fetchList(title: String, startIndex: Int): Single<BookList> {
        return service.getList(title, startIndex)
    }
}