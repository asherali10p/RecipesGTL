package com.asher.recipesgtl.di

import android.content.Context
import androidx.room.Room
import com.asher.recipesgtl.db.DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DBModule {

  @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext appContext: Context): DB {

                return Room.databaseBuilder(
                        appContext,
                        DB::class.java,
                        "room_database"
                ).fallbackToDestructiveMigration().build()
        }

}
