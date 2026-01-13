package com.example.meal.util

import com.example.meal.data.cache.AppDatabase
import com.example.meal.data.cache.DataCache
import com.example.meal.data.remote.dto.ChickenData
import com.google.gson.Gson

object ResponseUtil {

    suspend fun computeResponse(response: ChickenData?, url: String, db: AppDatabase): String {
        if (response != null) {
            val gson = Gson()
            val jsonResponse = gson.toJson(response)
            db.dataCacheDao().insertCache(DataCache(url, jsonResponse, System.currentTimeMillis()))
            return jsonResponse
        }
        return ""
    }
}
