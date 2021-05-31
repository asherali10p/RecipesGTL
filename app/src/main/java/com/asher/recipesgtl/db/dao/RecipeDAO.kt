package com.asher.recipesgtl.db.dao

import androidx.room.*
import com.asher.recipesgtl.models.RecipeDetails
import com.asher.recipesgtl.models.Recipes

@Dao
interface RecipeDAO {

    @Query("SELECT EXISTS(SELECT * FROM recipe_details WHERE id == :recipeId AND created_date < :timeout)")
    fun hasRecipe(recipeId: Long, timeout: Long): Boolean

    @Query("SELECT * FROM recipe_details WHERE id = :recipeId")
    fun getRecipeById(recipeId: Long): RecipeDetails?

    /*@Query("SELECT * FROM recipe_details")
    fun getAll(): List<RecipeDetails>

    @Query("SELECT * FROM recipe_details WHERE title LIKE :title")
    fun findByTitle(title: String): List<RecipeDetails>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(recipeDetails: RecipeDetails)

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipeDetailsList: List<RecipeDetails>)

    @Delete
    fun delete(recipeDetails: RecipeDetails)*/
}