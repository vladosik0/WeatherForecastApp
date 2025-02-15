package com.vladosik0.weather_forecast_app

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladosik0.weather_forecast_app.data.CurrentWeatherRepository
import com.vladosik0.weather_forecast_app.data.DefaultCurrentWeatherRepository
import com.vladosik0.weather_forecast_app.data.network.CurrentWeatherApiService
import com.vladosik0.weather_forecast_app.domain.check_network_connection.AndroidConnectivityObserver
import com.vladosik0.weather_forecast_app.domain.check_network_connection.ConnectivityObserver
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val currentWeatherRepository: CurrentWeatherRepository
    val connectivityViewModel: ConnectivityViewModel

}

class ConnectivityViewModel(
    connectivityObserver: ConnectivityObserver
): ViewModel() {

    val isConnected = connectivityObserver
        .isConnected
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            true
        )
}

class DefaultAppContainer(context: Context) : AppContainer {

    private val baseUrl = "https://api.weatherapi.com/v1/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitCurrentWeatherApiService : CurrentWeatherApiService by lazy {
        retrofit.create(CurrentWeatherApiService::class.java)
    }

    override val connectivityViewModel = ConnectivityViewModel(
        connectivityObserver = AndroidConnectivityObserver(context = context)
    )

    override val currentWeatherRepository: CurrentWeatherRepository by lazy {
        DefaultCurrentWeatherRepository(retrofitCurrentWeatherApiService)
    }

}