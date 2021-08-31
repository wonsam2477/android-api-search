package com.eddiej.apisearch.model.repository

import com.eddiej.apisearch.model.data.BookList
import io.reactivex.rxjava3.core.Single

class BookRepository {
    private val remoteSource = BookRemoteSource()

    fun getList(title: String, startIndex: Int = 1): Single<BookList> {
        return remoteSource.fetchList(title, startIndex)
    }
}