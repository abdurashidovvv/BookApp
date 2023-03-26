package com.example.bookapp.view.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapp.data.models.modelCategories.Result
import com.example.bookapp.databinding.CategoriesItemBinding

class CategoriesRVAdapter(var categoriesList: List<Result>, val setOnclickHelper: SetOnclickHelper) :
    RecyclerView.Adapter<CategoriesRVAdapter.CategoriesVh>() {

    inner class CategoriesVh(private val binding: CategoriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(categoriesModel: Result) {
            binding.categoriesItemTv.text = categoriesModel.display_name
            binding.root.setOnClickListener { setOnclickHelper.categoryClick(categoriesModel.list_name) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVh {
        return CategoriesVh(CategoriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoriesVh, position: Int) {
        holder.onBind(categoriesList[position])
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    interface RvClick{
        fun onClick(title:String)
    }
}