package com.example.meal.presentation

sealed class Screen(val route: String) {
    object MealListScreen : Screen("meal_list_screen")
    object MealDetailScreen : Screen("meal_detail_screen")
}
