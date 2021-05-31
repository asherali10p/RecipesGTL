package com.asher.recipesgtl.views.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.asher.recipesgtl.R
import com.asher.recipesgtl.adapters.ViewPagerFragmentAdapter
import com.asher.recipesgtl.databinding.ActivityRecipeDetailsBinding
import com.asher.recipesgtl.viewmodels.RecipeDetailsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeDetailsActivity : AppCompatActivity() {

    private val viewModel by viewModels<RecipeDetailsViewModel>()
    private val binding : ActivityRecipeDetailsBinding by lazy {
        DataBindingUtil.setContentView<ActivityRecipeDetailsBinding>(this, R.layout.activity_recipe_details)
    }
    private val titles = listOf<String>("Summary", "Ingredients", "Instructions")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        getExtras()

        binding.viewPager.adapter = ViewPagerFragmentAdapter(this,titles)

        // attaching tab mediator

        // attaching tab mediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {
                tab: TabLayout.Tab, position: Int ->
            tab.setText(titles.get(position)) }.attach()

        viewModel.recipeDetails.observe(this, Observer {
            Log.e("RecipeListActivity", it.toString())
        })
    }

    private fun getExtras() {
        val recipeId = intent?.getLongExtra("recipeID", -1)
        viewModel.fetchRecipeDetails(recipeId)
    }
}