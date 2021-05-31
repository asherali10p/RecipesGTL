package com.asher.recipesgtl.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asher.recipesgtl.constants.API_KEY
import com.asher.recipesgtl.models.RecipeDetails
import com.asher.recipesgtl.repositories.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
     val recipeRepository: RecipeRepository,
) : ViewModel() {

     var recipeDetails: MutableLiveData<RecipeDetails> = MutableLiveData()

    fun fetchRecipeDetails(recipeId: Long?) {

        if (recipeId == null || recipeId < 0) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val recp = recipeRepository.getRecipeById(recipeId)
            recipeDetails.postValue(recp)
        }


    }
}