package com.asher.recipesgtl.di

import com.asher.recipesgtl.db.dao.RecipeDAO
import com.asher.recipesgtl.repositories.RecipeRepository
import com.asher.recipesgtl.repositories.RecipeRepositoryImpl
import com.asher.recipesgtl.services.RecipesWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeRepositoryModule{

    @Singleton
    @Provides
    fun provideRecipeRepository(recipeService: RecipesWebService,recipeDao: RecipeDAO): RecipeRepository = RecipeRepositoryImpl(recipeService, recipeDao)


}