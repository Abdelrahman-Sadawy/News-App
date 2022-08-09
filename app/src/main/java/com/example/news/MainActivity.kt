package com.example.news

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.Api.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsapi.org/"

class MainActivity : AppCompatActivity() {

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imageList = mutableListOf<String>()
    private var urlList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewItems()

        requestNews()
    }

    private fun recyclerViewItems() {
        val rvNews: RecyclerView = findViewById(R.id.rvNews)
        rvNews.layoutManager = LinearLayoutManager(applicationContext)
        rvNews.adapter = NewsAdapter(titleList, descList, imageList, urlList)

    }

    private fun addToList(title: String, description: String, image: String, url: String) {
        titleList.add(title)
        descList.add(description)
        imageList.add(image)
        urlList.add(url)
    }

    private fun requestNews() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.news(
                    "Apple",
                    "2022-08-09",
                    "popularity",
                    "7ef0389e3ade4cc697a457f608679099"
                )

                for (article in response.articles) {
                    Log.i("MainActivity", "Result = $article")
                    addToList(article.title, article.description, article.urlToImage, article.url)
                }

                withContext(Dispatchers.Main) {
                    recyclerViewItems()
                }
            } catch (e: Exception) {
                Log.e("MainActivity", e.toString())
            }
        }
    }
}

