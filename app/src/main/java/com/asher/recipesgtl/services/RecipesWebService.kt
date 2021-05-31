package com.asher.recipesgtl.services

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.asher.recipesgtl.constants.URL_GET_RECIPES
import com.asher.recipesgtl.constants.URL_GET_RECIPE_BY_ID
import com.asher.recipesgtl.models.Recipes
import com.asher.recipesgtl.models.RecipeDetails

interface RecipesWebService {

    @GET(URL_GET_RECIPES)
    suspend fun getRecipes( @Query("query") query: String, @Query("apiKey") apiKey: String): Recipes?

    @GET(URL_GET_RECIPE_BY_ID)
    suspend fun getRecipeDetails( @Path("recipe_id") recipeID: Long, @Query("apiKey") apiKey: String): RecipeDetails?
}