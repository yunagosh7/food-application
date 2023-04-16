package com.example.foodapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapplication.network.MealApi
import com.example.foodapplication.pojo.CategoryList
import com.example.foodapplication.pojo.CategoryMeal
import com.example.foodapplication.pojo.Meal
import com.example.foodapplication.pojo.MealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var _randomMeal = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal> = _randomMeal
    private var _popularItems = MutableLiveData<List<CategoryMeal>>()
    val popularItems: LiveData<List<CategoryMeal>> = _popularItems

    fun getRandomMeal() {
        MealApi.api.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    _randomMeal.value = response.body()!!.meals[0]
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment", "Error ${t.message.toString()}")
            }
        })
    }

    fun getPopularItems() {
        MealApi.api.getPopularItems("Seafood").enqueue(object: Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body() != null) {
                    _popularItems.value = response.body()!!.meals
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeViewModel", "Error: ${t.message}")
            }
        })
    }

}