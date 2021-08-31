package com.eddiej.apisearch.feature.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eddiej.apisearch.feature.BaseViewModel
import com.eddiej.apisearch.model.data.Book
import com.eddiej.apisearch.model.repository.BookRepository
import io.reactivex.rxjava3.kotlin.addTo

class BookViewModel : BaseViewModel() {
    private val _repository = BookRepository()

    private val _bookList: MutableLiveData<List<Book>> = MutableLiveData()
    val bookList: LiveData<List<Book>>
        get() = _bookList

    override fun init() {
        // nothing
    }

    fun getList(title: String) {
        _repository.getList(title)
            .subscribeOn(getSchedulerProvider().io())
            .map { result -> result.items }
            .observeOn(getSchedulerProvider().ui())
            .subscribe({ list ->
                list?.let {
                    _bookList.postValue(list)
                }
            }, { error ->

            })
            .addTo(getDisposable())
    }

}