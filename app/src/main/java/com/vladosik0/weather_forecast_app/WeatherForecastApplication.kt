package com.vladosik0.weather_forecast_app

import android.app.Application

class WeatherForecastApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}