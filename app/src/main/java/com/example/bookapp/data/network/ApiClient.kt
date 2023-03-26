package com.example.bookapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {

    val BASE_URL = "https://api.nytimes.com"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    fun getApiSerivice(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }
}