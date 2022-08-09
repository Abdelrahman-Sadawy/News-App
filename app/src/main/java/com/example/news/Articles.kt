package com.example.news

data class Articles(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)