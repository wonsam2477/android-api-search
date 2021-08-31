package com.eddiej.apisearch.feature.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.eddiej.apisearch.feature.BaseViewModel
import com.eddiej.apisearch.model.data.Book
import com.eddiej.apisearch.model.repository.BookRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable

class BookViewModel : BaseViewModel() {
    private val _repository = BookRepository()

    private val _queryText = MutableLiveData<String>()
    val queryText: LiveData<String>
        get() = _queryText

    private val _selectedItem = MutableLiveData<Book>()
    val selectedItem: LiveData<Book>
        get() = _selectedItem

    fun pushQuery(query: String) {
        _queryText.postValue(query)
    }

    fun moveToDetail(item: Book) {
        _selectedItem.value = item
    }

    fun getList(query: String): Observable<PagingData<Book>> {
        return _repository
            .getList(query)
            .observeOn(AndroidSchedulers.mainThread())
            .cachedIn(viewModelScope)
    }
}