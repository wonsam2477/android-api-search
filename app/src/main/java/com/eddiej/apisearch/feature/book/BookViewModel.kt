package com.eddiej.apisearch.feature.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.eddiej.apisearch.domain.data.BookItemEntity
import com.eddiej.apisearch.feature.BaseViewModel
import com.eddiej.apisearch.model.data.BookItemModel
import com.eddiej.apisearch.model.repository.BookRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable

class BookViewModel : BaseViewModel() {
    private val _repository = BookRepository()

    private val _queryText = MutableLiveData<String>()
    val queryText: LiveData<String>
        get() = _queryText

    private val _selectedItem = MutableLiveData<BookItemEntity>()
    val selectedItem: LiveData<BookItemEntity>
        get() = _selectedItem

    // 검색창에서 받아온 결과 텍스트
    fun pushQuery(query: String) {
        _queryText.postValue(query)
    }

    // 상세정보 화면으로 이동
    fun moveToDetail(item: BookItemEntity) {
        _selectedItem.value = item
    }

    // 해당 검색어로 데이터 요청
    fun getList(query: String): Observable<PagingData<BookItemEntity>> {
        return _repository
            .getList(query)
            .observeOn(AndroidSchedulers.mainThread())
            .cachedIn(viewModelScope)
    }
}