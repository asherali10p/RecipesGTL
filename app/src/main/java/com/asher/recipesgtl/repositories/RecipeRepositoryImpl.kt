package com.asher.recipesgtl.repositories

import com.asher.recipesgtl.constants.API_KEY
import com.asher.recipesgtl.db.dao.RecipeDAO
import com.asher.recipesgtl.helpers.getPastDaysMillis
import com.asher.recipesgtl.models.RecipeDetails
import com.asher.recipesgtl.models.Recipes
import com.asher.recipesgtl.services.RecipesWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RecipeRepositoryImpl @Inject constructor(
    private val recipesWebService: RecipesWebService,
    private val recipeDAO: RecipeDAO
) : RecipeRepository{

    override suspend fun getAllRecipes(query: String, api_key: String): Recipes? {
        return withContext(Dispatchers.IO) {
            return@withContext recipesWebService.getRecipes(query, API_KEY)
        }
    }

    override suspend fun getRecipeById(recipe_id: Long): RecipeDetails? {

        return withContext(Dispatchers.IO) {

            val hasRecipe = recipeDAO.hasRecipe(recipe_id, getPastDaysMillis(7))
            if (hasRecipe) {
                return@withContext recipeDAO.getRecipeById(recipe_id)
            }
            val fetchedRecipe = recipesWebService.getRecipeDetails(recipe_id, API_KEY)
            if (fetchedRecipe != null) {
                 recipeDAO.insertOne(fetchedRecipe)

            }
            return@withContext fetchedRecipe
        }
        }

}