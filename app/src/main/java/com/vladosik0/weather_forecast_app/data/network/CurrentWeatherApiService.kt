package com.vladosik0.weather_forecast_app.data.network

import com.vladosik0.weather_forecast_app.BuildConfig
import com.vladosik0.weather_forecast_app.data.network.dto.CurrentLocationWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApiService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") location: String,
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("aqi") aqi: String = "no"
    ) : Response<CurrentLocationWeatherDto>
}