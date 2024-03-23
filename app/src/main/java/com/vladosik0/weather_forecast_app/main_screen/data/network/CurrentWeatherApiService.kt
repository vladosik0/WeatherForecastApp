package com.vladosik0.weather_forecast_app.main_screen.data.network

import com.vladosik0.weather_forecast_app.BuildConfig
import com.vladosik0.weather_forecast_app.main_screen.data.network.dto.CurrentLocationWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrentWeatherApiService {
    @GET("?key={key}&q={location}")
    suspend fun getCurrentWeather(
        @Path("q") location: String,
        @Path("key") key: String = BuildConfig.API_KEY,
        ) : Response<CurrentLocationWeatherDto>

}