package com.example.meal.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataCacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(dataCache: DataCache)

    @Query("SELECT * FROM data_cache WHERE url = :url")
    suspend fun getCache(url: String): DataCache?
}