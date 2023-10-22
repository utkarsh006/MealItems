package com.example.meal.data.remote

import com.example.meal.data.remote.dto.ChickenData
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("api/json/v1/1/search.php")
    suspend fun getMeals(@Query("s") mealId: String): ChickenData
}

/* This is a Retrofit Api interface, we basically defines different functions and routes we want to
access from our api */
