package com.asher.recipesgtl.di

import com.asher.recipesgtl.constants.BASE_URL
import com.asher.recipesgtl.services.RecipesWebService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipesWebServiceModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    fun provideApiService(retrofit: Retrofit) : RecipesWebService =
        retrofit.create(RecipesWebService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String, gson: Gson): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient =
        OkHttpClient
            .Builder()
            .build()


    @Singleton
    @Provides fun getGson(): Gson {
        return GsonBuilder().setLenient().create()
    }
}