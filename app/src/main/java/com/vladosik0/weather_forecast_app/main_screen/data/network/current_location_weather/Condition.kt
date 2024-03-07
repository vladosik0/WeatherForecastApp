package com.vladosik0.weather_forecast_app.main_screen.data.network.current_location_weather

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)