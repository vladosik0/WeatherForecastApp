package com.vladosik0.weather_forecast_app

import android.app.Application
import com.vladosik0.weather_forecast_app.data.AppContainer
import com.vladosik0.weather_forecast_app.data.DefaultAppContainer

class WeatherForecastApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}