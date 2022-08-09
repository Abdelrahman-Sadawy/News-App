package com.example.news.Api

import com.example.news.Articles
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/everything/")
    suspend fun news(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("sortBy") sort : String,
        @Query("apiKey") key : String
    ): Articles
}
