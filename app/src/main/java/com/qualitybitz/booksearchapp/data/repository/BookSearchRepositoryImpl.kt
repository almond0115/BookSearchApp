package com.qualitybitz.booksearchapp.data.repository

import com.qualitybitz.booksearchapp.data.api.RetrofitInstance
import com.qualitybitz.booksearchapp.data.model.SearchResponse
import retrofit2.Response

class BookSearchRepositoryImpl : BookSearchRepository {
    override suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Response<SearchResponse> {
        // Retrofit API에 searchBooks를 실행시켜 Response를 반환받도록 구현하면 됨
        return RetrofitInstance.api.searchBooks(query, sort, page, size)
    }
}