package com.asher.recipesgtl.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.asher.recipesgtl.constants.API_KEY
import com.asher.recipesgtl.models.LoadingState
import com.asher.recipesgtl.models.Recipe
import com.asher.recipesgtl.models.Recipes
import com.asher.recipesgtl.repositories.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor( val repository: RecipeRepository) : ViewModel(){

    private val _loading = MutableLiveData<LoadingState>()
    val loading: LiveData<LoadingState>
        get() = _loading

     var recipeList : MutableLiveData<Recipes> = MutableLiveData()

    init {
        viewModelScope.launch {
            fetchData("")
        }
    }

    fun fetchData(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loading.postValue(LoadingState.LOADING)
                val response = repository.getAllRecipes(query, API_KEY)
                if (response != null) {
                    recipeList.postValue(response)
                    _loading.postValue(LoadingState.LOADED)
                } else {
                    _loading.postValue(LoadingState.error("Something went wrong"))
                }

            } catch (e: Exception) {
                _loading.postValue(LoadingState.error(e.message))
            }
        }
    }

    private suspend fun getRecipes(query: String) {
        recipeList.value = repository.getAllRecipes(query, API_KEY)
    }
}