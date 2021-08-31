package com.eddiej.apisearch.model.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class NaverAuthInterceptor: Interceptor {
    // API Key
    private val CLIENT_ID = "klo_XycB4W03_UZcviSE"
    private val CLIENT_SECRET = "rbEAnMvA96"

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader("X-Naver-Client-Id", CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
            .build()

        return chain.proceed(newRequest)
    }
}