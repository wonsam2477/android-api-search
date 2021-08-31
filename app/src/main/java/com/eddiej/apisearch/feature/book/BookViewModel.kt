package com.eddiej.apisearch.feature.book

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.eddiej.apisearch.feature.BaseViewModel
import com.eddiej.apisearch.model.data.Book
import com.eddiej.apisearch.model.repository.BookRepository
import io.reactivex.rxjava3.core.Observable

class BookViewModel : BaseViewModel() {
    private val _repository = BookRepository()

    fun getList(query: String): Observable<PagingData<Book>> {
        return _repository
            .getList(query)
            .observeOn(getSchedulerProvider().ui())
            .cachedIn(viewModelScope)
    }
}