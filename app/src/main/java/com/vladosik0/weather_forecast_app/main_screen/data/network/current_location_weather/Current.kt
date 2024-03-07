package com.vladosik0.weather_forecast_app.main_screen.data.network.current_location_weather

data class Current(
    val condition: Condition,
    val is_day: Int,
    val temp_c: Double
)