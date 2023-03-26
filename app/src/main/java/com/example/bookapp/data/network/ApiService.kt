package com.example.bookapp.data.network

import com.example.bookapp.data.models.modelBooks.FullOverView
import com.example.bookapp.data.models.modelCategories.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.bookapp.data.models.modelBookByCategory.BookListByCategory
import com.example.bookapp.data.models.searchWithTitle.SearchResponse
import com.example.bookapp.utils.Constants.API_KEY

interface ApiService {

    @GET("/svc/books/v3/lists/full-overview.json")
    fun getBestsellersOverview(
        @Query("api-key") apiKey: String
    ): Call<FullOverView>

    @GET("/svc/books/v3/lists/names.json")
    fun getAllCategories(
        @Query("api-key") apiKey: String
    ):Call<Category>

    @GET("/svc/books/v3/lists/current/{path}.json")
    fun getBookListByCategory(
        @Path("path") path:String,
        @Query("api-key") apiKey: String
    ):Call<BookListByCategory>

    @GET("/svc/books/v3/reviews.json?title={title}")
    fun searchWithTitle(
        @Path("title") title:String,
        @Query("api-key") apiKey: String
    ):Call<SearchResponse>

}