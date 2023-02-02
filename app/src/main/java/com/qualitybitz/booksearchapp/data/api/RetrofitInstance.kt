package com.qualitybitz.booksearchapp.data.api

import com.qualitybitz.booksearchapp.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// 여러 개의 Retrofit 객체가 만들어지면 자원도 낭비되고 통신 혼선 발생 가능성 존재
object RetrofitInstance { // Object와 lazy를 조합하여 실제 사용하는 순간이 되어서비로소야 비로소 만들어지게 됨
    private val okHttpClient: OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }

    val api: BookSearchApi by lazy {
        retrofit.create(BookSearchApi::class.java)
    }
}