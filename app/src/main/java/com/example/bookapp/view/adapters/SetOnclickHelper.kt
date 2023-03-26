package com.example.bookapp.view.adapters

import com.example.bookapp.data.models.modelBooks.Book
import com.example.bookapp.data.models.modelCategories.Category

interface SetOnclickHelper {

    fun categoryClick(title:String)
    fun bookRvClick(book: Book)
    fun categoryBookClick(book: com.example.bookapp.data.models.modelBookByCategory.Book)
}