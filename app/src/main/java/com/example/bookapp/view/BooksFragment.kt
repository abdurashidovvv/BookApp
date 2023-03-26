package com.example.bookapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bookapp.R
import com.example.bookapp.data.db.MySharedPreference
import com.example.bookapp.data.db.SaveBook
import com.example.bookapp.data.models.modelBookByCategory.Book
import com.example.bookapp.databinding.FragmentBooksBinding
import com.squareup.picasso.Picasso


@Suppress("DEPRECATION")
class BooksFragment : Fragment() {

    private val binding by lazy { FragmentBooksBinding.inflate(layoutInflater) }
    private val TAG = "BooksFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        MySharedPreference.init(binding.root.context)
        val list = ArrayList<String>()
        list.addAll(MySharedPreference.obektString.map { it -> it.title })
        when (arguments?.getInt("check")) {
            1 -> {
                val categoryBook = arguments?.getSerializable("categoryBook") as Book
                binding.tvDisplayName1.text = categoryBook.title
                binding.tvDisplayName2.text = categoryBook.author
                Picasso.get().load(categoryBook.book_image).into(binding.image2)
                binding.tvRating.text = categoryBook.rank.toString()
                binding.tvPrice.text = categoryBook.price
                binding.tvDesc.text = categoryBook.description
                if (list.contains(categoryBook.title)) {
                    binding.imageSave.setImageResource(R.drawable.ic_savong)
                }
            }
            2 -> {
                val savedBook = arguments?.getSerializable("savedBook") as SaveBook
                binding.tvDisplayName1.text = savedBook.title
                binding.tvDisplayName2.text = savedBook.author
                Picasso.get().load(savedBook.imageLink).into(binding.image2)
                binding.tvRating.text = savedBook.rank.toString()
                binding.tvPrice.text = savedBook.price
                binding.tvDesc.text = savedBook.desc
                if (list.contains(savedBook.title)) {
                    binding.imageSave.setImageResource(R.drawable.ic_savong)
                }
            }
        }
        binding.imageBack.setOnClickListener { findNavController().popBackStack() }



        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val check = arguments?.getInt("check")

        binding.imageSave.setOnClickListener {
            if (check == 1) {
                val categoryBook = arguments?.getSerializable("categoryBook") as Book
                val book = SaveBook(
                    categoryBook.title,
                    categoryBook.author,
                    categoryBook.rank,
                    categoryBook.price,
                    categoryBook.description,
                    categoryBook.book_image
                )
                saving(book)
            } else if (check == 2) {
                val savedBook = arguments?.getSerializable("savedBook") as SaveBook
                val book = SaveBook(
                    savedBook.title,
                    savedBook.author,
                    savedBook.rank,
                    savedBook.price,
                    savedBook.desc,
                    savedBook.imageLink
                )
                saving(book)
            }

        }
    }


    private fun saving(book: SaveBook) {
        MySharedPreference.init(binding.root.context)
        val list = MySharedPreference.obektString
        binding.apply {
            if (list.contains(book)) {
                imageSave.setImageResource(R.drawable.ic_savong)
            }
            Picasso.get().load(book.imageLink).into(image2)
            tvDisplayName1.text = book.title
            tvDisplayName2.text = book.author
            tvRating.text = book.rank.toString()
            tvPrice.text = book.price
            tvDesc.text = book.desc

            imageSave.setOnClickListener {
                if (list.contains(book)) {
                    list.remove(book)
                    MySharedPreference.obektString = list
                    Toast.makeText(context, "O'chirildi", Toast.LENGTH_SHORT).show()
                    imageSave.setImageResource(R.drawable.save)
                } else {
                    list.add(book)
                    MySharedPreference.obektString = list
                    Toast.makeText(context, "Saqlandi", Toast.LENGTH_SHORT).show()
                    imageSave.setImageResource(R.drawable.ic_savong)
                }
            }
        }
    }
}