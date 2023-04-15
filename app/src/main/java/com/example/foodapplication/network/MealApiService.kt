package com.example.foodapplication.network

import com.example.foodapplication.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php")
    fun getById(@Query("i") id: String): Call<MealList>
}