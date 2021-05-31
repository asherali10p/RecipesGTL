package com.asher.recipesgtl.models

data class Recipes (
        val results: List<Recipe>?,
        val baseUri: String,
        val offset: Int,
        val number: Int,
        val totalResults: Int,
        val processingTimeMs: Int,
        val expires: Long,
        val isStale: Boolean
        ){
}