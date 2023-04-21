package com.example.foodapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapplication.network.MealApi
import com.example.foodapplication.pojo.Category
import com.example.foodapplication.pojo.CategoryList
import com.example.foodapplication.pojo.MealsByCategory
import com.example.foodapplication.pojo.MealsByCategoryList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesMealViewModel : ViewModel() {
    private var _categoryList = MutableLiveData<List<MealsByCategory>>()
    val categoryList: LiveData<List<MealsByCategory>> = _categoryList


    fun getMealsByCategory(mealCategory: String) {
        MealApi.api.getMealsByCategory(mealCategory).enqueue(object: Callback<MealsByCategoryList> {
            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>
            ) {
                if (response.body() != null) {
                    _categoryList.value = response.body()!!.meals
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.e("CategoriesMealViewModel", "Error: ${t.message}")
            }
        } )
    }

}