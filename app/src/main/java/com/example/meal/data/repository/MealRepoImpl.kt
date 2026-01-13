package com.example.meal.data.repository

import com.example.meal.common.Constants
import com.example.meal.data.cache.AppDatabase
import com.example.meal.data.remote.FoodApi
import com.example.meal.data.remote.dto.ChickenData
import com.example.meal.data.remote.dto.MealDTO
import com.example.meal.domain.repository.MealRepository
import com.example.meal.util.ResponseUtil.computeResponse
import com.google.gson.Gson
import javax.inject.Inject

class MealRepoImpl @Inject constructor(
    private val api: FoodApi,
    private val db: AppDatabase
) : MealRepository {

    override suspend fun getMeals(mealId: String): List<MealDTO> {
        val url = "${Constants.BASE_URL}api/json/v1/1/search.php?s=$mealId"
        val cachedData = fetchData(url)
        return if (cachedData.isNotEmpty()) {
            // Parse cached JSON back to ChickenData and return meals
            val gson = Gson()
            val chickenData = gson.fromJson(cachedData, ChickenData::class.java)
            chickenData.meals
        } else {
            emptyList()
        }
    }

    private suspend fun fetchData(url: String): String {
        val cachedData = db.dataCacheDao().getCache(url)
        if (cachedData != null && System.currentTimeMillis() - cachedData.timestamp < Constants.CACHE_VALIDITY) {
            return cachedData.json // Return cached data if still valid.
        } else {
            try {
                val response = api.getMeals(url.substringAfterLast("s="))
                return computeResponse(response, url, db)
            } catch (e: Exception) {
                e.printStackTrace()
                return ""
            }
        }
    }
}
