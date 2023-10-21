package com.example.meal.di

import com.example.meal.common.Constants
import com.example.meal.data.remote.FoodApi
import com.example.meal.data.repository.MealRepoImpl
import com.example.meal.domain.repository.MealRepository
import com.example.meal.domain.usecases.get_meals.GetMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun provideMealRepository(foodApi: FoodApi): MealRepository {
        return MealRepoImpl(foodApi)
    }

    @Provides
    @Singleton
    fun provideMealUseCase(mealRepo: MealRepository) : GetMealsUseCase{
        return GetMealsUseCase(mealRepo)
    }
}