package com.qualitybitz.booksearchapp.data.api

import com.qualitybitz.booksearchapp.data.model.SearchResponse
import com.qualitybitz.booksearchapp.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BookSearchApi {
    //
    @Headers("Authorization: KakaoAK $API_KEY") // 인증에 필요한 Headers
    @GET("v3/search/book") // GET 요청에 필요한 주소
    suspend fun searchBooks(
        // 인터페이스의 메소드인 searchBooks는 SearchResponse 타입을 가지는 Response 클래스를 반환하도록 정의
        // 나머지 parameter는 query annotation을 사용해 전달하게 된다
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<SearchResponse>
}