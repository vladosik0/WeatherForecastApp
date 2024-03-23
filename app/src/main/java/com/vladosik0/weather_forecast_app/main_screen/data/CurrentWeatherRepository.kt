package com.vladosik0.weather_forecast_app.main_screen.data

import com.vladosik0.weather_forecast_app.main_screen.data.mappers.toCurrent
import com.vladosik0.weather_forecast_app.main_screen.data.mappers.toLocation
import com.vladosik0.weather_forecast_app.main_screen.data.network.CurrentWeatherApiService
import com.vladosik0.weather_forecast_app.main_screen.domain.CurrentLocationWeather

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(location: String) : CurrentLocationWeather?
}

class DefaultCurrentWeatherRepository(
    private val currentWeatherApiService: CurrentWeatherApiService
) : CurrentWeatherRepository {
    override suspend fun getCurrentWeather(location: String): CurrentLocationWeather? {
        return try {
            val result = currentWeatherApiService.getCurrentWeather(location)
            if(result.isSuccessful) {
                CurrentLocationWeather(
                    result.body()!!.current.toCurrent(),
                    result.body()!!.location.toLocation()
                )
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}