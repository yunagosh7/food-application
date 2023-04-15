package com.example.foodapplication.network

import com.example.foodapplication.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApiService {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>
}