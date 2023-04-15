package com.example.foodapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapplication.network.MealApi
import com.example.foodapplication.pojo.Meal
import com.example.foodapplication.pojo.MealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var _randomMeal = MutableLiveData<Meal>()
    var randomMeal: LiveData<Meal> = _randomMeal

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

}