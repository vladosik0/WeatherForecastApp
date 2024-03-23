package com.vladosik0.weather_forecast_app.main_screen.domain

data class Current (
    val condition: Condition,
    val isDay: Boolean,
    val temperatureInCelsius: Double
)