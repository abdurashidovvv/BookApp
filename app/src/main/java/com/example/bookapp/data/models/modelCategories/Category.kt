package com.example.bookapp.data.models.modelCategories

data class Category(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)