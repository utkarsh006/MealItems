package com.example.meal.data.repository

import android.util.Log
import com.example.meal.data.remote.dto.MealDTO
import com.example.meal.data.remote.FoodApi
import com.example.meal.domain.model.Meal
import com.example.meal.domain.repository.MealRepository
import javax.inject.Inject

class MealRepoImpl @Inject constructor(
    private val api: FoodApi
) : MealRepository {

    override suspend fun getMeals(mealId: String): List<MealDTO> {
        val response =  api.getMeals("https://www.themealdb.com/api/json/v1/1/search.php/",mealId)
        //Log.d("MYTAG", response.toString())
        return response.meals
    }
}