package com.eddiej.apisearch.model.repository

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.eddiej.apisearch.domain.data.BookEntity
import com.eddiej.apisearch.domain.data.BookItemEntity
import com.eddiej.apisearch.global.Constants
import com.eddiej.apisearch.model.data.toMap
import com.eddiej.apisearch.model.network.RetrofitClient
import com.eddiej.apisearch.model.network.interceptor.NaverAuthInterceptor
import com.eddiej.apisearch.model.network.service.BookService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class BookPagingSource(private val service: BookService, private val query: String) : RxPagingSource<Int, BookItemEntity>() {

    override fun getRefreshKey(state: PagingState<Int, BookItemEntity>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1) ?: state.closestPageToPosition(
                anchorPos
            )?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, BookItemEntity>> {
        // 시작 인덱스를 1로 설정
        val nextPage = params.key ?: 1
        Log.d("PagingSource", "key : ${params.key ?: 1}, loadSize : ${params.loadSize}")
        return service.getList(query, nextPage)
            .subscribeOn(Schedulers.io())
            .map { data -> data.toMap() }
            .map { data -> toResult(data, nextPage) }
            .onErrorReturn { error -> LoadResult.Error(error) }
    }

    private fun toResult(data: BookEntity, page: Int): LoadResult<Int, BookItemEntity> {
        // prevKey 필드는 추후 이전 목록 불러오기 기능을 사용할 때 설정할 것

        return LoadResult.Page(
            data = data.items,
            prevKey = null,
            nextKey = if (page > data.total) null else page.plus(data.display)
        )
    }
}