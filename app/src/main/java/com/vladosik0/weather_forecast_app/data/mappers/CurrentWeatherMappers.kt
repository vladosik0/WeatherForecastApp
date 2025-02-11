package com.vladosik0.weather_forecast_app.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.vladosik0.weather_forecast_app.data.network.dto.ConditionDto
import com.vladosik0.weather_forecast_app.data.network.dto.CurrentDto
import com.vladosik0.weather_forecast_app.data.network.dto.LocationDto
import com.vladosik0.weather_forecast_app.domain.Condition
import com.vladosik0.weather_forecast_app.domain.Current
import com.vladosik0.weather_forecast_app.domain.Location
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


fun CurrentDto.toCurrent() : Current {
    return Current (
        condition = condition.toCondition(),
        isDay = is_day == 1,
        temperatureInCelsius = temp_c.roundToInt()
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocationDto.toLocation() : Location {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val dateTime = LocalDateTime.parse(localtime, inputFormatter)

    val systemZone = ZoneId.systemDefault()

    val zonedDateTime = dateTime.atZone(systemZone)
    val systemCurrentTime = ZonedDateTime.now(systemZone)

    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val adjustedTime = zonedDateTime.withMinute(systemCurrentTime.minute)

    val formattedDate = zonedDateTime.format(dateFormatter)
    val formattedTime = adjustedTime.format(timeFormatter)

    return Location (
        localTime = formattedTime,
        localDate = formattedDate,
        name = name
    )
}

fun ConditionDto.toCondition() : Condition {
    return Condition (
        text = text,
        icon = "https:$icon"
    )
}