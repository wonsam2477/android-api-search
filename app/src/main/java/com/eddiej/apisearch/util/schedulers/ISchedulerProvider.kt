package com.eddiej.apisearch.util.schedulers

import io.reactivex.rxjava3.core.Scheduler


interface ISchedulerProvider {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
    fun new(): Scheduler
}