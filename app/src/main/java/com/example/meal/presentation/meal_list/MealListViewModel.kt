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

    init {
        getMeals("Chicken")
    }

    private fun getMeals(mealId: String) {
        getMealsUseCase.setMealID(mealId)
        getMealsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MealListState(meals = result.data ?: emptyList())
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
}
