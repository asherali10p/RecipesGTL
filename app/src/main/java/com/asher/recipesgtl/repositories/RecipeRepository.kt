package com.asher.recipesgtl.repositories

import com.asher.recipesgtl.models.Recipe
import com.asher.recipesgtl.models.RecipeDetails
import com.asher.recipesgtl.models.Recipes

interface RecipeRepository {

    suspend fun getAllRecipes(query: String, api_key: String) : Recipes?
    suspend fun getRecipeById(recipe_id: Long) : RecipeDetails?

}