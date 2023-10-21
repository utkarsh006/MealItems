package com.example.meal.domain.repository

import com.example.meal.data.remote.dto.MealDTO

interface MealRepository {
    suspend fun getMeals(mealId: String): List<MealDTO>
}