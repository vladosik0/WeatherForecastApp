package com.vladosik0.weather_forecast_app.domain

data class Current (
    val condition: Condition = Condition(),
    val isDay: Boolean = false,
    val temperatureInCelsius: Int = 0
)