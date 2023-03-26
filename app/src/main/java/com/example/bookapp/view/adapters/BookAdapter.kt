package com.example.bookapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bookapp.data.models.modelBooks.Book
import com.example.bookapp.databinding.ItemBookBinding
import com.squareup.picasso.Picasso

class BookAdapter(var list: List<Book>, val setOnclickHelper: SetOnclickHelper) :
    RecyclerView.Adapter<BookAdapter.Vh>() {

    inner class Vh(private val itemBook: ItemBookBinding) : ViewHolder(itemBook.root) {
        fun onBind(book: Book) {
            Picasso.get().load(book.book_image).into(itemBook.imageView)
            itemBook.trendingBooksText.text = book.title
            itemBook.root.setOnClickListener {
                setOnclickHelper.bookRvClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}