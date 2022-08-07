package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.Api.retrofit
import retrofit2.Call
import  retrofit2.Callback
import  retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = NewsAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.rvNews)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        recyclerView.adapter = adapter


        requestNews()
    }

    private fun requestNews() {
        retrofit.news()
            .enqueue(object : Callback<List<News>> {
                override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                    if(response.isSuccessful) {
                        Log.d("***", "onResponse ${response.body().toString()}")
                    } else {
                        Log.d("***", "onResponse ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<News>>, t: Throwable) {
                    Log.d("***", "onFailure ${t.localizedMessage}")
                }
            })
    }
}

