package com.example.foodapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MealApi {

    val api: MealApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApiService::class.java)
    }
}