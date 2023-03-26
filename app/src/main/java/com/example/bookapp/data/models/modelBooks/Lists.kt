package com.example.bookapp.data.models.modelBooks

data class Lists(
    val books: List<Book>,
    val display_name: String,
    val list_id: Int,
    val list_image: Any,
    val list_image_height: Any,
    val list_image_width: Any,
    val list_name: String,
    val list_name_encoded: String,
    val updated: String
)