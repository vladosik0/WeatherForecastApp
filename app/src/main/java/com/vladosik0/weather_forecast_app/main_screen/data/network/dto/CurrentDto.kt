package com.vladosik0.weather_forecast_app.main_screen.data.network.dto


data class CurrentDto(
    val condition: ConditionDto,
    val is_day: Int,
    val temp_c: Double
)