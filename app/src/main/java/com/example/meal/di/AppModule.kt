package com.example.meal.di

import android.content.Context
import androidx.room.Room
import com.example.meal.common.Constants
import com.example.meal.data.cache.AppDatabase
import com.example.meal.data.remote.FoodApi
import com.example.meal.data.repository.MealRepoImpl
import com.example.meal.domain.repository.MealRepository
import com.example.meal.domain.usecases.get_meals.GetMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "cache-database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePaprikaApi(): FoodApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodApi::class.java)
    }

    //This function we would provide to our repository
    @Provides
    @Singleton
    fun provideMealRepository(foodApi: FoodApi, appDatabase: AppDatabase): MealRepository {
        return MealRepoImpl(foodApi, appDatabase)
    }

    @Provides
    @Singleton
    fun provideMealUseCase(mealRepo: MealRepository) : GetMealsUseCase{
        return GetMealsUseCase(mealRepo)
    }
}