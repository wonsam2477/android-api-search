package com.eddiej.apisearch.feature.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.eddiej.apisearch.feature.BaseViewModel
import com.eddiej.apisearch.model.data.Book
import com.eddiej.apisearch.model.repository.BookRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.ExperimentalCoroutinesApi

class BookViewModel : BaseViewModel() {
    private val _repository = BookRepository()

    private val _bookList: MutableLiveData<List<Book>> = MutableLiveData()
    val bookList: LiveData<List<Book>>
        get() = _bookList

    private val _bookPagingData = MutableLiveData<PagingData<Book>>()
    val bookPagingData: LiveData<PagingData<Book>>
        get() = _bookPagingData

    fun getList(query: String):Observable<PagingData<Book>> {
        return _repository
            .getList(query)
//            .subscribeOn(getSchedulerProvider().io())
            .cachedIn(viewModelScope)
//            .observeOn(getSchedulerProvider().ui())
//            .subscribe { pagingData -> _bookPagingData.postValue(pagingData) }
//            .addTo(getDisposable())

        /*_repository.getList(title)
            .subscribeOn(getSchedulerProvider().io())
            .map { result -> result.items }
            .observeOn(getSchedulerProvider().ui())
            .subscribe({ list ->
                list?.let {
                    _bookList.postValue(list)
                }
            }, { error ->

            })
            .addTo(getDisposable())*/
    }

}