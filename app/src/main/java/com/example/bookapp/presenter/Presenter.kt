package com.example.bookapp.presenter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bookapp.data.models.modelBooks.Book
import com.example.bookapp.data.models.modelBooks.FullOverView
import com.example.bookapp.data.models.modelCategories.Category
import com.example.bookapp.utils.MyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.bookapp.data.models.modelBookByCategory.BookListByCategory
import java.util.*
import kotlin.collections.ArrayList

class Presenter(
    private var homeView: Contract.View.HomeFragment,
    private var bookView: Contract.View.BookFragment,
    private var model: Contract.Model,
) : Contract.Presenter, Contract.Model.FinishedListener {

    private val TAG = "Presenter"

    override fun onClickBookButton(book: Book) {
        MyData.book = book
    }


    override fun swipeRefresh() {
        // TODO:
    }

    override fun onCreateUI() {
        homeView.showProgress()

        //Get All Book
        model.getFullOverView(object : Callback<FullOverView> {
            override fun onResponse(call: Call<FullOverView>, response: Response<FullOverView>) {
                homeView.hideProgress()
                val result = response.body()
                if (result != null) {
                    homeView.showBooks(book = result)
                }
            }

            override fun onFailure(call: Call<FullOverView>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })


        //Get All Categories
        model.getAllCategory(object : Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                homeView.hideProgress()
                val result = MutableLiveData<Category>()
                result.postValue(response.body())
                homeView.showCategory(category = result)
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
            }
        })


    }


    //GetAllBooksByCategory
    override fun rvItemClick(path: String) {
        homeView.showProgress()
        model.getAllBooksByCategory(path, object : Callback<BookListByCategory> {
            override fun onResponse(
                call: Call<BookListByCategory>,
                response: Response<BookListByCategory>
            ) {
                val result: BookListByCategory?
                if (response.isSuccessful) {
                    result = response.body()
                    homeView.hideProgress()
                    homeView.showCategoriesBook(result)
                }
            }

            override fun onFailure(call: Call<BookListByCategory>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
            }
        })
    }

    override fun searchEdtListener(title: String) {
        model.getFullOverView(object : Callback<FullOverView> {
            override fun onResponse(call: Call<FullOverView>, response: Response<FullOverView>) {
                val result: FullOverView?
                if (response.isSuccessful) {
                    result = response.body()
                    val list = ArrayList<Book>()
                    try {
                        Log.d(TAG, "Result: $result")
                        for (i in 0 until result!!.results.lists.size) {
                            for (j in 0 until result.results.lists[i].books.size) {
                                if (result.results.lists[i].books[j].title.lowercase(Locale.ROOT)
                                        .substring(0, title.length) == title.lowercase(Locale.ROOT)
                                ) {
                                    list.add(result.results.lists[i].books[j])
                                }
                            }
                        }
                        Log.d(TAG, "onResponse: $list")
                        homeView.searchBooks(list)
                    } catch (e: Exception) {
                        Log.d(TAG, "onResponse: ${e.message}")
                    }
                }
            }

            override fun onFailure(call: Call<FullOverView>, t: Throwable) {

            }
        })
    }


    override fun onFinished() {
        homeView.hideProgress()
    }
}