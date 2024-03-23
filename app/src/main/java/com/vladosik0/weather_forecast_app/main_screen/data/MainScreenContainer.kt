package com.vladosik0.weather_forecast_app.main_screen.data

import com.vladosik0.weather_forecast_app.main_screen.data.network.CurrentWeatherApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface MainScreenContainer {
    val currentWeatherRepository: CurrentWeatherRepository
}

class DefaultMainScreenContainer() : MainScreenContainer {
    private val baseUrl = "http://api.weatherapi.com/v1/current.json"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitCurrentWeatherApiService : CurrentWeatherApiService by lazy {
        retrofit.create(CurrentWeatherApiService::class.java)
    }

    override val currentWeatherRepository: CurrentWeatherRepository by lazy {
        DefaultCurrentWeatherRepository(retrofitCurrentWeatherApiService)
    }

}