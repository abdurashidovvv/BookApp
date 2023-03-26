package com.example.bookapp.data.models.modelBooks

data class FullOverView(
    val copyright: String,
    val num_results: Int,
    val results: Results,
    val status: String
)