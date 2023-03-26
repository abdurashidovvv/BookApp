package com.example.bookapp.presenter

import androidx.lifecycle.MutableLiveData
import com.example.bookapp.data.models.modelBooks.Book
import com.example.bookapp.data.models.modelBooks.FullOverView
import com.example.bookapp.data.models.modelCategories.Category
import retrofit2.Callback
import com.example.bookapp.data.models.modelBookByCategory.BookListByCategory

interface Contract {

    interface View {
        interface HomeFragment {
            fun hideProgress()
            fun showProgress()
            fun showCategory(category: MutableLiveData<Category>)
            fun showBooks(book: FullOverView)
            fun showCategoriesBook(bookListByCategory: BookListByCategory?)
            fun searchBooks(fullOverView: ArrayList<Book>)
        }

        interface BookFragment {
            fun setBook()
        }
    }

    interface Model {
        interface FinishedListener {
            fun onFinished()
        }

        fun getFullOverView(callback: Callback<FullOverView>): FullOverView?
        fun getAllCategory(callback: Callback<Category>): Category?
        fun getAllBooksByCategory(path: String, callback: Callback<BookListByCategory>): BookListByCategory?
    }

    interface Presenter {
        fun onClickBookButton(book: Book)
        fun swipeRefresh()
        fun onCreateUI()
        fun rvItemClick(path:String)
        fun searchEdtListener(title:String)
    }
}