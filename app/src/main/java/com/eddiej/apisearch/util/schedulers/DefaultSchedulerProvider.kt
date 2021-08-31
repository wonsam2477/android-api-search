package com.eddiej.apisearch.util.schedulers

import com.eddiej.apisearch.util.schedulers.ISchedulerProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers


class DefaultSchedulerProvider : ISchedulerProvider {
    override fun computation(): Scheduler = Schedulers.computation()

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun new(): Scheduler = Schedulers.newThread()
}