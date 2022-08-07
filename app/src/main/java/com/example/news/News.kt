package com.example.news

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("title")
    val title : String,

    @SerializedName("author")
    val author : String,

    @SerializedName("url")
    val url : String,

    @SerializedName("urlToImage")
    val imageUrl : String
) {
}