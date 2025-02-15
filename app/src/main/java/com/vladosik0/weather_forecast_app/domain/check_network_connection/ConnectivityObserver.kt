package com.vladosik0.weather_forecast_app.domain.check_network_connection

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val isConnected: Flow<Boolean>
}