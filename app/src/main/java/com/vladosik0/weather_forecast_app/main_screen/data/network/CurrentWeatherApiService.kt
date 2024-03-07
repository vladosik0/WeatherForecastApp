package com.vladosik0.weather_forecast_app.main_screen.data.network

import com.vladosik0.weather_forecast_app.main_screen.data.network.current_location_weather.CurrentLocationWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrentWeatherApiService {
    @GET("?key={key}&q={location}")
    suspend fun getCurrentWeather(@Path("key") key: String, @Path("q") location: String) : Response<CurrentLocationWeather>

}