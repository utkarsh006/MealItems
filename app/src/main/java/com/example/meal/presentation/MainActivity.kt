package com.example.meal.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meal.domain.model.Meal
import com.example.meal.presentation.meal_detail.MealDetailScreen
import com.example.meal.presentation.meal_list.MealListScreen
import com.example.meal.presentation.ui.MealTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MealListScreen.route
                    ) {
                        composable(
                            route = Screen.MealListScreen.route
                        ) {
                            MealListScreen(navController)
                        }

                        composable(
                            route = Screen.MealDetailScreen.route + "?meal={meal}",
                            arguments = listOf(
                                navArgument("meal") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val meal = Gson().fromJson(it.arguments?.getString("meal"), Meal::class.java)
                            MealDetailScreen(meal)
                        }
                    }
                }
            }
        }
    }
}
