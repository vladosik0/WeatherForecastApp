package com.vladosik0.weather_forecast_app.data

import com.vladosik0.weather_forecast_app.data.network.CurrentWeatherApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val currentWeatherRepository: CurrentWeatherRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://api.weatherapi.com/v1/"

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