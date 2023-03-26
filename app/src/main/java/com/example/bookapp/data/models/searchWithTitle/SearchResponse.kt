package com.example.bookapp.data.models.searchWithTitle

data class SearchResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)