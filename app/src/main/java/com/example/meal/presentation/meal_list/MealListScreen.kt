package com.example.meal.presentation.meal_list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meal.presentation.Screen
import com.example.meal.presentation.meal_list.components.MealListItem
import com.google.gson.Gson

@Composable
fun MealListScreen(
    navController: NavController,
    viewModel: MealListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Log.d("List", state.meals.toString())
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.meals) { meal ->

                MealListItem(
                    meal = meal,
                    onItemClicked = {
                        val mealString = Gson().toJson(meal)
                        navController.navigate(
                            Screen.MealDetailScreen.route + "?meal=$mealString"
                        )
                    }
                )
            }
        }

        // If the error occurs
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
