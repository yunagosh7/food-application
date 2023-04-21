package com.example.foodapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodapplication.pojo.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    // Reemplazando el elemento ya en la DB tiene el mismo efecto que un UPDATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(meal: Meal)

    @Update
    fun updateMeal(meal: Meal)

    @Delete
    fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM meal_info")
    fun getMeals(): LiveData<List<Meal>>
}