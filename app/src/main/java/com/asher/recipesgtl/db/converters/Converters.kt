package com.asher.recipesgtl.db.converters

import androidx.room.TypeConverter
import com.asher.recipesgtl.models.Ingredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromIngredientList(countryLang: List<Ingredient>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Ingredient>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toIngredientList(countryLangString: String?): List<Ingredient>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Ingredient>?>() {}.type
        return gson.fromJson<List<Ingredient>>(countryLangString, type)
    }
}