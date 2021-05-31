package com.asher.recipesgtl.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.asher.recipesgtl.db.converters.Converters
import java.util.*

@Entity(tableName = "recipe_details")
@TypeConverters( Converters::class)
data class RecipeDetails(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "created_date") val createdDate: Date,
    val title: String,
    val summary: String,
    val instructions: String,
    val extendedIngredients: List<Ingredient>,
    val image: String,
    val vegetarian: Boolean,
    val vegan: Boolean,
    @ColumnInfo(name = "gluten_free") val glutenFree: Boolean,
    @ColumnInfo(name = "dairy_free")val dairyFree: Boolean,
    @ColumnInfo(name = "very_healthy")val veryHealthy: Boolean,
    val cheap: Boolean,
    @ColumnInfo(name = "very_popular")val veryPopular: Boolean,
    val sustainable: Boolean,
    @ColumnInfo(name = "weight_watcher_smart_points")val weightWatcherSmartPoints: Int,
    val gaps: String,
    @ColumnInfo(name = "low_fod_map")val lowFodmap: Boolean,
    @ColumnInfo(name = "aggregate_likes")val aggregateLikes: Int,
    @ColumnInfo(name = "spoonacular_score")val spoonacularScore: Int,
    @ColumnInfo(name = "health_score")val healthScore: Int,
    @ColumnInfo(name = "price_per_serving")val pricePerServing: Float,
    @ColumnInfo(name = "ready_in_minutes")val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String
    )
