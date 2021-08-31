package com.eddiej.apisearch.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import androidx.paging.rxjava3.observable
import com.eddiej.apisearch.model.data.Book
import com.eddiej.apisearch.model.data.BookList
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi

class BookRepository {
    fun getList(query: String): Observable<PagingData<Book>> {
        return Pager(config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true,
            prefetchDistance = 5
        ),
            pagingSourceFactory = { BookPagingSource(query) })
            .observable
    }
}