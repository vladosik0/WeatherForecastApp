package com.vladosik0.weather_forecast_app.data.network.dto


data class CurrentLocationWeatherDto(
    val current: CurrentDto,
    val location: LocationDto
)