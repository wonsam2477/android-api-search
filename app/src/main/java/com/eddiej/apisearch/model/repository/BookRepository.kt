package com.eddiej.apisearch.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import com.eddiej.apisearch.domain.data.BookItemEntity
import com.eddiej.apisearch.model.data.BookItemModel
import io.reactivex.rxjava3.core.Observable

class BookRepository {
    fun getList(query: String): Observable<PagingData<BookItemEntity>> {
        return Pager(config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true,
            prefetchDistance = 5
        ),
            pagingSourceFactory = { BookPagingSource(query) })
            .observable
    }
}