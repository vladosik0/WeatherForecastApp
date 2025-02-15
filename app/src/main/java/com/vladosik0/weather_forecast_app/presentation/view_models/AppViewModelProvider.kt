package com.vladosik0.weather_forecast_app.presentation.view_models

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vladosik0.weather_forecast_app.WeatherForecastApplication


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MainScreenViewModel(
                weatherForecastApplication().container.currentWeatherRepository,
                isConnected = weatherForecastApplication().container.connectivityViewModel.isConnected
            )
        }
    }
}

fun CreationExtras.weatherForecastApplication(): WeatherForecastApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WeatherForecastApplication)