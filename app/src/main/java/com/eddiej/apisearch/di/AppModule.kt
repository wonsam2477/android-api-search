package com.eddiej.apisearch.di

import com.eddiej.apisearch.global.Constants
import com.eddiej.apisearch.model.network.RetrofitClient
import com.eddiej.apisearch.model.network.interceptor.NaverAuthInterceptor
import com.eddiej.apisearch.model.network.service.BookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBookService(): BookService {
        return RetrofitClient.get(Constants.NAVER_API_SEARCH, listOf(NaverAuthInterceptor()))
            .create(BookService::class.java)
    }
}