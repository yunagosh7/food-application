package com.example.foodapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapplication.databinding.ItemPopularBinding
import com.example.foodapplication.pojo.CategoryList
import com.example.foodapplication.pojo.CategoryMeal
import com.example.foodapplication.pojo.Meal

class MostPopularAdapter(
    var mealsList: List<CategoryMeal> = emptyList(),
    val onItemSelected: (CategoryMeal) -> Unit
) : RecyclerView.Adapter<MostPopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularViewHolder {
        return MostPopularViewHolder(ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MostPopularViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularImage)

        holder.itemView.setOnClickListener {
            onItemSelected.invoke(mealsList[position])
        }

    }


    override fun getItemCount(): Int = mealsList.size

}