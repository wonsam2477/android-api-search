package com.eddiej.apisearch.model.network

import com.eddiej.apisearch.global.App
import com.eddiej.apisearch.model.network.interceptor.NaverAuthInterceptor
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    fun get(baseUrl: String, interceptors: List<Interceptor> = listOf()): Retrofit {
        val httpClient = OkHttpClient.Builder()

        // 10mb
        val cacheSize = (10 * 512 * 1024).toLong()
        val cache = Cache(App.instance.cacheDir, cacheSize)

        httpClient.connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(NaverAuthInterceptor())
            .cache(cache)
            .retryOnConnectionFailure(true)

        if (interceptors.isNotEmpty()) {
            interceptors.forEach {
                httpClient.addInterceptor(it)
            }
        }

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }
}