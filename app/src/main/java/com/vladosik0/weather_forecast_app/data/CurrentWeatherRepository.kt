package com.vladosik0.weather_forecast_app.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.vladosik0.weather_forecast_app.data.mappers.toCurrent
import com.vladosik0.weather_forecast_app.data.mappers.toLocation
import com.vladosik0.weather_forecast_app.data.network.CurrentWeatherApiService
import com.vladosik0.weather_forecast_app.domain.CurrentLocationWeather
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(location: String) : CurrentLocationWeather
    suspend fun getFavouritePlacesWeathers(locations: List<String>) : List<CurrentLocationWeather>
}

class DefaultCurrentWeatherRepository(
    private val currentWeatherApiService: CurrentWeatherApiService
) : CurrentWeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getCurrentWeather(location: String): CurrentLocationWeather {
        return try {
            val result = currentWeatherApiService.getCurrentWeather(location)
            if(result.isSuccessful) {
                println("Success")
                CurrentLocationWeather(
                    result.body()!!.current.toCurrent(),
                    result.body()!!.location.toLocation()
                )
            } else {
                println("Error occurred because of API problem")
                CurrentLocationWeather()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error occurred in catch block")
            CurrentLocationWeather()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getFavouritePlacesWeathers(locations: List<String>): List<CurrentLocationWeather> {
        val favouritePlacesWeathers = mutableListOf<Deferred<CurrentLocationWeather>>()
        coroutineScope {
            for(location in locations) {
                val favouritePlaceWeather = async {
                    getCurrentWeather(location)
                }
                favouritePlacesWeathers.add(favouritePlaceWeather)
            }
        }
        return favouritePlacesWeathers.awaitAll()
    }
}