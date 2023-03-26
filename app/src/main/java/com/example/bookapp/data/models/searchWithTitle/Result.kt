package com.example.bookapp.data.models.searchWithTitle

data class Result(
    val book_author: String,
    val book_title: String,
    val byline: String,
    val isbn13: List<String>,
    val publication_dt: String,
    val summary: String,
    val uri: String,
    val url: String,
    val uuid: String
)