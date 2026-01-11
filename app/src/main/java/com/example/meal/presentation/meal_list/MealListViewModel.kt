package com.example.meal.presentation.meal_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meal.common.Resource
import com.example.meal.domain.usecases.get_meals.GetMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MealListState())
    val state: State<MealListState> get() = _state // exposing this state to the composables

    // Cache for search functionality
    private var fullMealsList: List<com.example.meal.domain.model.Meal> = emptyList()

    init {
        getMeals("Chicken")
    }

    private fun getMeals(mealId: String) {
        getMealsUseCase.setMealID(mealId)
        getMealsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val meals = result.data ?: emptyList()
                    _state.value = MealListState(meals = meals)

                    // Cache the full list once
                    if (fullMealsList.isEmpty()) {
                        fullMealsList = meals
                    }
                }

                is Resource.Error -> {
                    _state.value = MealListState(error = result.message ?: "Unexpected Error")
                }

                is Resource.Loading -> {
                    _state.value = MealListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    // Search functionality
    fun searchMeals(query: String) {
        _state.value = _state.value.copy(
            searchQuery = query,
            meals = if (query.isBlank()) {
                fullMealsList
            } else {
                fullMealsList.filter {
                    it.strMeal.contains(query, ignoreCase = true)
                }
            }
        )
    }
}
