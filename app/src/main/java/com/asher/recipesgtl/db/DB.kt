package com.asher.recipesgtl.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.asher.recipesgtl.db.converters.Converters
import com.asher.recipesgtl.db.dao.RecipeDAO
import com.asher.recipesgtl.models.RecipeDetails
import java.security.AccessControlContext

@Database(entities = [RecipeDetails::class], version = 1)
@TypeConverters(Converters::class)
abstract class DB: RoomDatabase() {

    abstract fun recipeDAO(): RecipeDAO

    companion object{
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getDB(context: Context): RoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                RoomDatabase::class.java,
                "room_database").fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}