package com.example.bookapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.bookapp.R
import com.example.bookapp.data.db.MySharedPreference
import com.example.bookapp.data.db.SaveBook
import com.example.bookapp.data.models.modelBookByCategory.BookListByCategory
import com.example.bookapp.data.models.modelBooks.Book
import com.example.bookapp.data.models.modelBooks.FullOverView
import com.example.bookapp.data.models.modelCategories.Category
import com.example.bookapp.data.network.ApiClient
import com.example.bookapp.data.network.ApiService
import com.example.bookapp.databinding.FragmentHomeBinding
import com.example.bookapp.databinding.NavHeaderItemBinding
import com.example.bookapp.model.Model
import com.example.bookapp.presenter.Contract
import com.example.bookapp.presenter.Presenter
import com.example.bookapp.view.adapters.*


class HomeFragment : Fragment(), Contract.View.HomeFragment,
    Contract.View.BookFragment, SetOnclickHelper {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var apiService: ApiService
    private lateinit var presenter: Presenter
    private lateinit var searchBookAdapter: BookAdapter
    private lateinit var categoriesRVAdapter: CategoriesRVAdapter
    private lateinit var bookAdapter: BookAdapter
    private lateinit var categoryBooksAdapter: CategoryBooksAdapter
    private lateinit var categoryBooks: ArrayList<com.example.bookapp.data.models.modelBookByCategory.Book>
    private lateinit var searchBooks: ArrayList<Book>
    private lateinit var categoryList: ArrayList<com.example.bookapp.data.models.modelCategories.Result>
    private val TAG = "HomeFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navHeaderItemBinding = NavHeaderItemBinding.inflate(layoutInflater)
        binding.navView.addHeaderView(navHeaderItemBinding.root)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //CategoryBooks
        categoryBooks = ArrayList()
        categoryBooksAdapter =
            CategoryBooksAdapter(categoryBooks, this)
        binding.topAuthorsRV.adapter = categoryBooksAdapter

        //CategoryList
        categoryList = ArrayList()
        categoriesRVAdapter = CategoriesRVAdapter(categoryList, this)
        binding.categoriesRV.adapter = categoriesRVAdapter

        //searchBook
        searchBooks = ArrayList()
        searchBookAdapter = BookAdapter(searchBooks, this)
        binding.searchBookRv.adapter = searchBookAdapter

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        apiService = ApiClient.getApiSerivice()
        presenter = Presenter(this, this, Model(apiService))
        presenter.onCreateUI()

        //Navigation View
        binding.navImage.setOnClickListener {
            binding.drawerLayout.open()
        }

        //Search
        binding.searchView.addTextChangedListener {
            if (it.toString() == "") {
                binding.apply {
                    categoriesRV.visibility = View.VISIBLE
                    topAuthorsRV.visibility = View.VISIBLE
                    trendingBooksRV.visibility = View.VISIBLE
                    categoriesTv.visibility = View.VISIBLE
                    topAuthorsTv.visibility = View.VISIBLE
                    trendingBooksTv.visibility = View.VISIBLE
                    searchBookRv.visibility = View.GONE
                }
                categoryBooksAdapter.notifyDataSetChanged()
                presenter.onCreateUI()
            } else {
                binding.apply {
                    categoriesRV.visibility = View.GONE
                    topAuthorsRV.visibility = View.GONE
                    trendingBooksRV.visibility = View.GONE
                    categoriesTv.visibility = View.GONE
                    topAuthorsTv.visibility = View.GONE
                    trendingBooksTv.visibility = View.GONE
                    searchBookRv.visibility = View.VISIBLE
                    presenter.searchEdtListener(it.toString())
                    Log.d(TAG, "onCreateView: $categoryBooks")
                    Log.d(TAG, "onCreateView: ${categoryBooks.size}")
                }
            }
        }
    }


    //Navigation View savedBooks
    private lateinit var navHeaderItemBinding: NavHeaderItemBinding
    override fun onStart() {
        super.onStart()
        MySharedPreference.init(binding.root.context)
        val list = MySharedPreference.obektString
        val rvSaveBookAdapter = RvSaveBookAdapter(list, object : RvSaveBookAdapter.RvClick {
            override fun onClick(saveBook: SaveBook) {
                findNavController().navigate(
                    R.id.booksFragment,
                    bundleOf("savedBook" to saveBook, "check" to 2)
                )
            }
        })
        navHeaderItemBinding.rvHeader.adapter = rvSaveBookAdapter
    }

    //Hide ProgressBar
    override fun hideProgress() {
        binding.progressAuthor.visibility = View.GONE
        binding.progressCategoryNames.visibility = View.GONE
        binding.progressTrendingBooks.visibility = View.GONE
    }

    //Show ProgressBar
    override fun showProgress() {
        binding.progressAuthor.visibility = View.VISIBLE
        binding.progressCategoryNames.visibility = View.VISIBLE
        binding.progressTrendingBooks.visibility = View.VISIBLE
    }


    //ShowCategory
    @SuppressLint("NotifyDataSetChanged")
    override fun showCategory(category: MutableLiveData<Category>) {
        category.observe(viewLifecycleOwner) {
            try {
                categoriesRVAdapter.categoriesList = it.results
                categoriesRVAdapter.notifyDataSetChanged()
            } catch (_: Exception) {
            }
        }
    }


    //TrendingBooks
    override fun showBooks(book: FullOverView) {
        val list = book.results
        val listAdapter = ArrayList<Book>()
        for (i in 0 until list.lists.size) {
            listAdapter.addAll(list.lists[i].books)
        }
        this.bookAdapter = BookAdapter(list = listAdapter, this)
        binding.trendingBooksRV.adapter = bookAdapter
    }


    //CategoryBooks
    @SuppressLint("NotifyDataSetChanged")
    override fun showCategoriesBook(bookListByCategory: BookListByCategory?) {
        categoryBooks.clear()
        if (bookListByCategory != null) {
            categoryBooks.addAll(bookListByCategory.results.books)
        }
        categoryBooksAdapter.list = categoryBooks
        categoryBooksAdapter.notifyDataSetChanged()
    }


    //Search Book
    @SuppressLint("NotifyDataSetChanged")
    override fun searchBooks(fullOverView: ArrayList<Book>) {
        searchBookAdapter.list = fullOverView
        searchBookAdapter.notifyDataSetChanged()
    }


    override fun setBook() {
        TODO(reason = "Not yet implemented")
    }

    //Category Item Click
    override fun categoryClick(title: String) {
        presenter.rvItemClick(title)
    }


    //Book Item Click
    override fun bookRvClick(book: Book) {
        val saveBook = SaveBook(
            book.title,
            book.author,
            book.rank,
            book.price,
            book.description,
            book.book_image
        )
        Log.d(TAG, "bookRvClick: $saveBook")
        findNavController().navigate(
            R.id.booksFragment,
            bundleOf("savedBook" to saveBook, "check" to 2)
        )
    }


    //CategoryBooks Item Click
    override fun categoryBookClick(book: com.example.bookapp.data.models.modelBookByCategory.Book) {
        findNavController().navigate(
            R.id.booksFragment,
            bundleOf("categoryBook" to book, "check" to 1)
        )
    }
}