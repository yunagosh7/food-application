package com.example.foodapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.foodapplication.databinding.ItemMealBinding
import com.example.foodapplication.pojo.Meal
import com.example.foodapplication.pojo.MealsByCategory

class CategoryMealsAdapter(
    val mealList: List<MealsByCategory> = emptyList(),
    val onItemClick: (MealsByCategory) -> Unit
) : RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewHolder {
        return CategoryMealsViewHolder(
            ItemMealBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: CategoryMealsViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealList[position].strMealThumb)
            .into(holder.binding.imgMealImage)
        holder.binding.tvMealName.text = mealList[position].strMeal
        holder.itemView.setOnClickListener {
            onItemClick(mealList[position])
        }
    }

    override fun getItemCount(): Int = mealList.size

    inner class CategoryMealsViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)

}