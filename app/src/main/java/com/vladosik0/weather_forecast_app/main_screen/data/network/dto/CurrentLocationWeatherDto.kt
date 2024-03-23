package com.vladosik0.weather_forecast_app.main_screen.data.network.dto


data class CurrentLocationWeatherDto(
    val current: CurrentDto,
    val location: LocationDto
)