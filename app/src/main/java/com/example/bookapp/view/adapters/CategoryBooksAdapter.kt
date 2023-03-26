package com.example.bookapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.data.models.modelBookByCategory.Book
import com.example.bookapp.databinding.TrendingItemBinding
import com.squareup.picasso.Picasso

class CategoryBooksAdapter(var list: List<Book>, val setOnclickHelper: SetOnclickHelper) : RecyclerView.Adapter<CategoryBooksAdapter.Vh>() {

    inner class Vh(var itemRv: TrendingItemBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(book: Book) {
            Picasso.get().load(book.book_image).into(itemRv.imageView)
            itemRv.trendingBooksText.text = book.title
            itemRv.root.setOnClickListener {
                setOnclickHelper.categoryBookClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(TrendingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface RvClick{
        fun onCLick(book: Book)
    }
}