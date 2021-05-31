package com.asher.recipesgtl.di

import android.content.Context
import androidx.room.Room
import com.asher.recipesgtl.db.DB
import com.asher.recipesgtl.db.dao.RecipeDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeDAOModule {


        @Provides
        fun provideRecipeDao(appDatabase: DB): RecipeDAO {
                return appDatabase.recipeDAO()
        }



}