package com.example.meal.data.remote

import com.example.meal.data.remote.dto.ChickenData
import com.example.meal.data.remote.dto.MealDTO
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface FoodApi {
    @GET
    suspend fun getMeals(@Url url: String, @Query("s") mealId: String): ChickenData

}

/* This is a Retrofit Api interface, we basically defines different functions and routes we want to
access from our api */