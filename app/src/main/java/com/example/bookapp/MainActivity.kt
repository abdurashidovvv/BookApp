package com.example.bookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookapp.data.models.modelBookByCategory.BookListByCategory
import com.example.bookapp.databinding.ActivityMainBinding
import com.example.bookapp.data.models.modelBooks.FullOverView
import com.example.bookapp.data.models.modelCategories.Category
import com.example.bookapp.data.network.ApiClient
import com.example.bookapp.data.network.ApiService
import com.example.bookapp.model.Model
import com.example.bookapp.presenter.Contract
import com.example.bookapp.presenter.Presenter
import com.example.bookapp.view.adapters.CategoriesRVAdapter

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}