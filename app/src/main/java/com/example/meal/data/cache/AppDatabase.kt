package com.example.meal.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataCache::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataCacheDao(): DataCacheDao
}