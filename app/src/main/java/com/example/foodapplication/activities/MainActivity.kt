package com.example.foodapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.foodapplication.R
import com.example.foodapplication.database.MealDatabase
import com.example.foodapplication.databinding.ActivityMainBinding
import com.example.foodapplication.viewmodel.HomeViewModel
import com.example.foodapplication.viewmodel.HomeViewModelFactory

class MainActivity : AppCompatActivity() {
    val viewModel: HomeViewModel by lazy {
        val mealDatabase = MealDatabase.getInstance(this)
        val homeViewModelFactory = HomeViewModelFactory(mealDatabase.mealDao())
        ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.fragment_container)

        NavigationUI.setupWithNavController(binding.btnNav, navController)

    }
}