package com.eddiej.apisearch.global

class App : android.app.Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
    }
}