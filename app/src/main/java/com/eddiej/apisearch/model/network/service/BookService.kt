package com.eddiej.apisearch.model.network.service

import com.eddiej.apisearch.model.data.BookModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    /**
     * [Naver Book API]
     * query	string	-	-	검색을 원하는 문자열로서 UTF-8로 인코딩한다.	상세검색시 생략가능
     * display	integer	N	10(기본값), 100(최대)	검색 결과 출력 건수 지정	-
     * start	integer	N	1(기본값), 1000(최대)	검색 시작 위치로 최대 1000까지 가능	-
     * sort	string	N	sim(기본값), date	정렬 옵션: sim(유사도순), date(출간일순), count(판매량순)
     */
    @GET("book.json")
    fun getList(
        @Query("query") query: String,
        @Query("start") startIndex: Int = 1,
        @Query("display") displayCount: Int = 10
    ): Single<BookModel>
}