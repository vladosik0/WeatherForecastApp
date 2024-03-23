package com.vladosik0.weather_forecast_app.main_screen.data.mappers

import com.vladosik0.weather_forecast_app.main_screen.data.network.dto.ConditionDto
import com.vladosik0.weather_forecast_app.main_screen.data.network.dto.CurrentDto
import com.vladosik0.weather_forecast_app.main_screen.data.network.dto.CurrentLocationWeatherDto
import com.vladosik0.weather_forecast_app.main_screen.data.network.dto.LocationDto
import com.vladosik0.weather_forecast_app.main_screen.domain.Condition
import com.vladosik0.weather_forecast_app.main_screen.domain.Current
import com.vladosik0.weather_forecast_app.main_screen.domain.CurrentLocationWeather
import com.vladosik0.weather_forecast_app.main_screen.domain.Location

fun CurrentLocationWeatherDto.toCurrentLocationWeather() : CurrentLocationWeather {
    return CurrentLocationWeather(
        current = current.toCurrent(),
        location = location.toLocation()
    )
}

fun CurrentDto.toCurrent() : Current {
    return Current (
        condition = condition.toCondition(),
        isDay = is_day == 1,
        temperatureInCelsius = temp_c
    )
}

fun LocationDto.toLocation() : Location {
    return Location (
        country = country,
        localTime = localtime,
        name = name
    )
}

fun ConditionDto.toCondition() : Condition {
    return Condition (
        code = code,
        icon = icon,
        text = text
    )
}