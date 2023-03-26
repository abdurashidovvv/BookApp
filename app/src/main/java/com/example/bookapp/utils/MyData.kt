package com.example.bookapp.utils

import com.example.bookapp.data.db.SaveBook
import com.example.bookapp.data.models.modelBooks.Book
import com.example.bookapp.data.models.modelBooks.FullOverView
import com.example.bookapp.data.models.modelCategories.Category
import com.example.bookapp.data.models.modelBookByCategory.BookListByCategory

object MyData {
    var book:Book?=null
    var categoryList:Category?=null
    var fullOverView:FullOverView?=null
    var bookListByCategory: BookListByCategory?=null
}