package com.example.foodapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodapplication.activities.CategoryMealsActivity
import com.example.foodapplication.activities.MainActivity
import com.example.foodapplication.activities.MealActivity
import com.example.foodapplication.adapters.CategoriesAdapter
import com.example.foodapplication.adapters.MostPopularAdapter
import com.example.foodapplication.databinding.FragmentHomeBinding
import com.example.foodapplication.pojo.Category
import com.example.foodapplication.pojo.MealsByCategory
import com.example.foodapplication.pojo.Meal
import com.example.foodapplication.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var randomMeal: Meal

    private lateinit var popularItemsAdapter: MostPopularAdapter
    private lateinit var categoriesListAdapter: CategoriesAdapter


    companion object {
        const val MEAL_ID = "meal_id"
        const val MEAL_NAME = "meal_name"
        const val MEAL_THUMB = "meal_thumb"
        const val MEAL_CATEGORY = "meal_category"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRandomMeal()

        viewModel.randomMeal.observe(viewLifecycleOwner, Observer {meal ->
            Glide.with(this)
                .load(meal.strMealThumb)
                .into(binding.imageRandomMeal)
            randomMeal = meal
        })

        onRandomClick()

        viewModel.getPopularItems()

        viewModel.popularItems.observe(viewLifecycleOwner, Observer {popularItemsList ->
            popularItemsAdapter = MostPopularAdapter(popularItemsList) {
                onPopularItemClick(it)
            }
            binding.recViewMealsPopular.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            binding.recViewMealsPopular.adapter = popularItemsAdapter
        })

        viewModel.getCategories()
        viewModel.categoryList.observe(viewLifecycleOwner, ) {
            categoriesListAdapter = CategoriesAdapter(it) {category ->
                onCategoryClick(category)
            }
            binding.recViewCategories.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            binding.recViewCategories.adapter = categoriesListAdapter
        }


    }

    private fun onPopularItemClick(mealsByCategory: MealsByCategory) {
        val intent = Intent(activity, MealActivity::class.java)
        intent.putExtra(MEAL_ID, mealsByCategory.idMeal)
        intent.putExtra(MEAL_NAME, mealsByCategory.strMeal)
        intent.putExtra(MEAL_THUMB, mealsByCategory.strMealThumb)
        startActivity(intent)
    }

    private fun onRandomClick() {
        binding.cardRandomMeal.setOnClickListener {
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun onCategoryClick(category: Category) {
        val intent = Intent(activity, CategoryMealsActivity::class.java)
        intent.putExtra(MEAL_CATEGORY, category.strCategory)
        startActivity(intent)
    }

}