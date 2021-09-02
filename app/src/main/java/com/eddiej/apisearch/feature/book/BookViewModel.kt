package com.eddiej.apisearch.feature.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.eddiej.apisearch.domain.data.BookItemEntity
import com.eddiej.apisearch.feature.BaseViewModel
import com.eddiej.apisearch.model.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val repository: BookRepository) : BaseViewModel() {

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
        return repository
            .getList(query)
            .observeOn(AndroidSchedulers.mainThread())
            .cachedIn(viewModelScope)
    }
}