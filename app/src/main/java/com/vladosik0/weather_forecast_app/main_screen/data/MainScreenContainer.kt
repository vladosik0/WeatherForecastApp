package com.vladosik0.weather_forecast_app.main_screen.data

import com.google.gson.internal.GsonBuildConfig
import com.vladosik0.weather_forecast_app.BuildConfig

interface MainScreenContainer {

}

class DefaultMainScreenContainer() : MainScreenContainer {
    val baseUrl = "http://api.weatherapi.com/v1/current.json"

}