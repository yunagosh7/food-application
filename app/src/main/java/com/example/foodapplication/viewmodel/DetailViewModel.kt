package com.example.foodapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodapplication.database.MealDao
import com.example.foodapplication.database.MealDatabase
import com.example.foodapplication.network.MealApi
import com.example.foodapplication.pojo.Meal
import com.example.foodapplication.pojo.MealList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(
    private val mealDao: MealDao
) : ViewModel() {
    private var _mealDetail = MutableLiveData<Meal>()
    val mealDetail: LiveData<Meal> = _mealDetail



    fun getById(id: String) {
        MealApi.api.getById(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    _mealDetail.value = response.body()!!.meals[0]
                }
                else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("DetailViewModel", "Error: ${t.message.toString()}")
            }
        })
    }

    fun insertMeal(meal: Meal) {
        CoroutineScope(Dispatchers.IO).launch {
            mealDao.insertMeal(meal)
        }
    }


}

class DetailViewModelFactory(private val mealDao: MealDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(mealDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}