package com.vladosik0.weather_forecast_app.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladosik0.weather_forecast_app.data.CurrentWeatherRepository
import com.vladosik0.weather_forecast_app.domain.CurrentLocationWeather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val currentWeatherRepository: CurrentWeatherRepository
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val favouritePlaces = listOf(
        "London", "Kyiv", "New York", "Madrid", "Amsterdam", "Lisbon", "Munich", "Tokyo"
    )
    private val _favouritePlacesWeathers = MutableStateFlow(listOf<CurrentLocationWeather>())
    val favouritePlacesWeathers = _favouritePlacesWeathers
        .onStart { getAllCurrentLocationWeathers() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            listOf()
        )

    private val currentPlace = "Lebedyn"
    private val _currentPlaceWeather = MutableStateFlow(CurrentLocationWeather())
    val currentPlaceWeather = _currentPlaceWeather
        .onStart { getCurrentPlaceWeather() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CurrentLocationWeather()
        )

    private fun getAllCurrentLocationWeathers() {
        viewModelScope.launch {
            _favouritePlacesWeathers.value = currentWeatherRepository.getFavouritePlacesWeathers(favouritePlaces)
            _isRefreshing.update { false }
        }
    }

    private fun getCurrentPlaceWeather() {
        viewModelScope.launch {
            _currentPlaceWeather.value = currentWeatherRepository.getCurrentWeather(currentPlace)
        }
    }

    fun onPullToRefreshTrigger() {
        _isRefreshing.update { true }
        getCurrentPlaceWeather()
        getAllCurrentLocationWeathers()
    }

}