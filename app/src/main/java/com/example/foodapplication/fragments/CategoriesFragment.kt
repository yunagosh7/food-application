package com.example.foodapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodapplication.R
import com.example.foodapplication.activities.CategoryMealsActivity
import com.example.foodapplication.activities.MainActivity
import com.example.foodapplication.activities.MealActivity
import com.example.foodapplication.adapters.CategoriesAdapter
import com.example.foodapplication.adapters.CategoryMealsAdapter
import com.example.foodapplication.databinding.FragmentCategoriesBinding
import com.example.foodapplication.pojo.Category
import com.example.foodapplication.pojo.MealsByCategory
import com.example.foodapplication.viewmodel.HomeViewModel

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.categoryList.observe(viewLifecycleOwner) { mealList ->

            categoriesAdapter = CategoriesAdapter(mealList) {
                onCategoryClick(it)
            }

            binding.rvCategories.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            binding.rvCategories.adapter = categoriesAdapter
        }


    }


    private fun onCategoryClick(category: Category) {
        val intent = Intent(activity, CategoryMealsActivity::class.java)
        intent.putExtra(HomeFragment.MEAL_CATEGORY, category.strCategory)
        startActivity(intent)
    }
}