package com.asher.recipesgtl.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.asher.recipesgtl.R
import com.asher.recipesgtl.adapters.RecipesAdapter
import com.asher.recipesgtl.databinding.ActivityRecipeListBinding
import com.asher.recipesgtl.models.Recipe
import com.asher.recipesgtl.viewmodels.RecipeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListActivity : AppCompatActivity() {

    private val viewModel by viewModels<RecipeListViewModel>()
    private val binding : ActivityRecipeListBinding by lazy {
        DataBindingUtil.setContentView<ActivityRecipeListBinding>(this, R.layout.activity_recipe_list).apply {
            lifecycleOwner = this@RecipeListActivity
            viewModel = viewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupList()
        setupSearch()
    }

    private fun setupList() {
        val recipesAdapter = RecipesAdapter {
            showRecipeDetails(it)
        }
        binding.adapter = recipesAdapter
        viewModel.recipeList.observe(this, Observer {
            Log.e("RecipeListActivity", it.toString())
            val list = it.results
            recipesAdapter.submitList(list)
        })

    }

    private fun setupSearch() {

        binding.searchView.onActionViewExpanded();
        binding.searchView.clearFocus();

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.fetchData(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun showRecipeDetails(recipe: Recipe) {
       Intent(this, RecipeDetailsActivity::class.java).apply {
            putExtra("recipeID", recipe.id)
            startActivity(this)
        }
    }
}