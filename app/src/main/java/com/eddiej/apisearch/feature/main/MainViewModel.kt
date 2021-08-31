package com.eddiej.apisearch.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eddiej.apisearch.feature.BaseViewModel
import com.eddiej.apisearch.global.LoadingState
import com.eddiej.apisearch.model.repository.BookRepository


class MainViewModel : BaseViewModel() {

    private val _repository = BookRepository()

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState
}