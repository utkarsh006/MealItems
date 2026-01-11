package com.example.meal.presentation.meal_list

import com.example.meal.domain.model.Meal

data class MealListState(
    val isLoading: Boolean = false,
    val meals: List<Meal> = emptyList(),
    val error: String = "",
    val searchQuery: String = ""
)
