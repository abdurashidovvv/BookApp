package com.example.bookapp.data.models.modelBookByCategory

data class BookListByCategory(
    val copyright: String,
    val last_modified: String,
    val num_results: Int,
    val results: Results,
    val status: String
)