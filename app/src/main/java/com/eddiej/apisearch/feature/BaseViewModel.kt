package com.eddiej.apisearch.feature

import android.util.Log
import androidx.lifecycle.ViewModel
import com.eddiej.apisearch.util.schedulers.DefaultSchedulerProvider
import com.eddiej.apisearch.util.schedulers.ISchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    companion object {
        val TAG: String = this::class.java.simpleName
    }

    private val _baseSchedulerProvider: ISchedulerProvider
    private val _compositeDisposable: CompositeDisposable

    init {
        _baseSchedulerProvider = DefaultSchedulerProvider()
        _compositeDisposable = CompositeDisposable()

        this.init()
    }

    abstract fun init()

    override fun onCleared() {
        Log.d(javaClass.simpleName, "${javaClass.simpleName} onCleared")
        _compositeDisposable.dispose()
        super.onCleared()
    }

    fun getSchedulerProvider(): ISchedulerProvider = _baseSchedulerProvider

    fun getDisposable(): CompositeDisposable = _compositeDisposable
    fun addDisposable(disposable: Disposable) {
        _compositeDisposable.add(disposable)
    }
}