package com.eddiej.apisearch.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import com.eddiej.apisearch.domain.data.BookItemEntity
import com.eddiej.apisearch.model.data.BookItemModel
import com.eddiej.apisearch.model.network.service.BookService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class BookRepository @Inject constructor(private val service: BookService) {
    fun getList(query: String): Observable<PagingData<BookItemEntity>> {
        return Pager(config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true,
            prefetchDistance = 3
        ),
            pagingSourceFactory = { BookPagingSource(service, query) })
            .observable
    }
}