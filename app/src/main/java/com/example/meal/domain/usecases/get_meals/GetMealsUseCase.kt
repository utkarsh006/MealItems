package com.example.meal.domain.usecases.get_meals

import com.example.meal.data.remote.dto.toMeal
import com.example.meal.common.Resource
import com.example.meal.domain.model.Meal
import com.example.meal.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(
    private val repository: MealRepository,
) {
    private var mealId: String ?= null

    fun setMealID(mealId:String){
        this.mealId = mealId
    }

    operator fun invoke(): Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())
            val meals = repository.getMeals(mealId!!).map { it.toMeal() }
            emit(Resource.Success(meals))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach Server"))
        }
    }
}