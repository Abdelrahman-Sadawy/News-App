package com.example.news.Api

import retrofit2.Call
import com.example.news.News
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsApi {
    @GET("https://newsapi.org/v2/")
    fun news(): Call<List<News>>
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://newsapi.org/v2/everything?q=tesla&from=2022-07-06&sortBy=publishedAt&apiKey=5ed14c5204fe49b9b47b5a392cb565a7")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(NewsApi::class.java)