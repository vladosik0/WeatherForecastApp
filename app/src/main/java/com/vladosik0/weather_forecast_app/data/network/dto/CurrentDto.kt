package com.vladosik0.weather_forecast_app.data.network.dto


data class CurrentDto(
    val condition: ConditionDto,
    val is_day: Int,
    val temp_c: Double
)