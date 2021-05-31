package com.asher.recipesgtl

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asher.recipesgtl.constants.API_KEY
import com.asher.recipesgtl.constants.BASE_URL
import com.asher.recipesgtl.db.DB
import com.asher.recipesgtl.db.dao.RecipeDAO
import com.asher.recipesgtl.repositories.RecipeRepository
import com.asher.recipesgtl.repositories.RecipeRepositoryImpl
import com.asher.recipesgtl.services.RecipesWebService
import com.asher.recipesgtl.viewmodels.RecipeListViewModel
import com.google.gson.GsonBuilder
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class RecipeListViewModelTest: TestCase() {

   private lateinit var recipeListViewModel: RecipeListViewModel
    private lateinit var repository: RecipeRepository
    private lateinit var recipesWebService: RecipesWebService
    private lateinit var recipeDAO: RecipeDAO

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    public override fun setUp() {

      val okHttpClient = OkHttpClient
            .Builder()
            .build()

       val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()

        recipesWebService = retrofit.create(RecipesWebService::class.java)
        recipeDAO = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), DB::class.java).build().recipeDAO()

        repository = RecipeRepositoryImpl(recipesWebService,recipeDAO)
        recipeListViewModel = RecipeListViewModel(repository)
        }

    // add more tests for more cases and manipulate db for additional cases
    @Test
    fun testRecipes() {

        runBlocking {
            val allRecipes = repository.getAllRecipes("", API_KEY)
            assertNotNull(allRecipes)

            val recipe = allRecipes?.results?.get(0)?.id?.let { repository.getRecipeById(it) }
            assertNotNull(recipe)
        }

    }
}