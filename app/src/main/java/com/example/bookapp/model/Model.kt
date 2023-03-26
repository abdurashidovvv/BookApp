package com.example.bookapp.model

import com.example.bookapp.data.models.modelBooks.FullOverView
import com.example.bookapp.data.models.modelCategories.Category
import com.example.bookapp.data.network.ApiService
import com.example.bookapp.presenter.Contract
import com.example.bookapp.utils.Constants.API_KEY
import com.example.bookapp.utils.MyData
import retrofit2.Callback
import com.example.bookapp.data.models.modelBookByCategory.BookListByCategory

class Model(private val apiService: ApiService) : Contract.Model {

    private val TAG = "Model"


    override fun getFullOverView(callback: Callback<FullOverView>): FullOverView? {
        apiService.getBestsellersOverview(API_KEY).enqueue(callback)
        return MyData.fullOverView
    }

    override fun getAllCategory(callback: Callback<Category>): Category? {
        apiService.getAllCategories(API_KEY).enqueue(callback)
        return MyData.categoryList
    }

    override fun getAllBooksByCategory(path:String, callback: Callback<BookListByCategory>): BookListByCategory? {
        apiService.getBookListByCategory(path, API_KEY).enqueue(callback)
        return MyData.bookListByCategory
    }

}