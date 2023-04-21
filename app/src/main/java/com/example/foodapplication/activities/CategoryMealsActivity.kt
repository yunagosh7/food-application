package com.example.foodapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapplication.R
import com.example.foodapplication.adapters.CategoryMealsAdapter
import com.example.foodapplication.databinding.ActivityCategoryMealsBinding
import com.example.foodapplication.fragments.HomeFragment
import com.example.foodapplication.pojo.CategoryList
import com.example.foodapplication.pojo.Meal
import com.example.foodapplication.pojo.MealsByCategory
import com.example.foodapplication.viewmodel.CategoriesMealViewModel

class CategoryMealsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryMealsBinding
    private lateinit var viewModel: CategoriesMealViewModel
    private lateinit var categoryMealsAdapter: CategoryMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CategoriesMealViewModel::class.java)

        val category = intent.getStringExtra(HomeFragment.MEAL_CATEGORY)

        viewModel.getMealsByCategory(category.orEmpty())
        viewModel.categoryList.observe(this, Observer {mealsList ->
            categoryMealsAdapter = CategoryMealsAdapter(mealsList) {meal ->
                onItemClick(meal




                )
            }
            binding.recMeals.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            binding.recMeals.adapter = categoryMealsAdapter
            binding.tvCategoryCount.text = getString(R.string.items, mealsList.size.toString())
        })

    }

    fun onItemClick(meal: MealsByCategory) {
        val intent = Intent(this, MealActivity::class.java)
        intent.putExtra(HomeFragment.MEAL_NAME, meal.strMeal)
        intent.putExtra(HomeFragment.MEAL_ID, meal.idMeal)
        intent.putExtra(HomeFragment.MEAL_THUMB, meal.strMealThumb)
        startActivity(intent)
    }
}