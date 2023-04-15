package com.example.foodapplication.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodapplication.R
import com.example.foodapplication.databinding.ActivityMealBinding
import com.example.foodapplication.fragments.HomeFragment
import com.example.foodapplication.viewmodel.DetailViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding
    private lateinit var viewModel: DetailViewModel

    // Datos del intent+
    private lateinit var mealName: String
    private lateinit var mealId: String
    private lateinit var mealThumb: String
    private lateinit var mealVideo: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        loadingCase()
        getInfoForIntent()

        initUI()

        viewModel.getById(mealId)

        initObservers()

        binding.imgYoutube.setOnClickListener{
            onYoutubeIconClick()
        }

    }


    private fun initObservers() {
        viewModel.mealDetail.observe(this, Observer {meal ->
            onResponseCase()
            binding.tvInstructionsSteps.text = meal.strInstructions
            binding.tvCategory.text = getString(R.string.category, meal.strCategory)
            binding.tvArea.text = getString(R.string.area, meal.strArea)
            mealVideo = meal.strYoutube
        })
    }

    private fun initUI() {
        Glide.with(this)
            .load(mealThumb)
            .into(binding.imgMealDetail)
        binding.collapsingToolbar.title = mealName


    }

    private fun getInfoForIntent() {
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME).orEmpty()
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID).orEmpty()
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB).orEmpty()
    }


    private fun loadingCase() {
        binding.progressBar.visibility = View.VISIBLE
        binding.fabFav.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }

    private fun onResponseCase() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.fabFav.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }

    private fun onYoutubeIconClick() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mealVideo))
        startActivity(intent)
    }

}