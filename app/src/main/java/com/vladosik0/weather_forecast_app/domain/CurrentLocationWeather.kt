package com.vladosik0.weather_forecast_app.domain

data class CurrentLocationWeather (
    val current: Current = Current(),
    val location: Location = Location()
)