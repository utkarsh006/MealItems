package com.example.meal.data.repository

import com.example.meal.data.remote.FoodApi
import com.example.meal.data.remote.dto.MealDTO
import com.example.meal.domain.repository.MealRepository
import javax.inject.Inject

class MealRepoImpl @Inject constructor(
    private val api: FoodApi
) : MealRepository {

    override suspend fun getMeals(mealId: String): List<MealDTO> {
        val response = api.getMeals(mealId)
        // Log.d("MyTag", response.toString())
        return response.meals
    }
}
