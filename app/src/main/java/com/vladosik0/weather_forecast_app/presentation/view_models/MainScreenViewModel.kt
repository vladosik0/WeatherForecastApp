package com.vladosik0.weather_forecast_app.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladosik0.weather_forecast_app.data.CurrentWeatherRepository
import com.vladosik0.weather_forecast_app.domain.data_serialization.CurrentLocationWeather
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val currentWeatherRepository: CurrentWeatherRepository,
    private val isConnected: Flow<Boolean>
) : ViewModel() {

    var mainScreenUiState: MainScreenUiState by mutableStateOf(MainScreenUiState.LOADING)
        private set

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>(replay = 1)
    val toastEvent: SharedFlow<String> = _toastEvent

    private val favouritePlaces = listOf(
        "London", "Kyiv", "New York", "Madrid", "Amsterdam", "Lisbon", "Munich", "Tokyo"
    )

    init {
        viewModelScope.launch {
            isConnected.collect { value ->
                if(!value) {
                    _toastEvent.emit("No network connection")
                }
            }
        }
    }

    private val _favouritePlacesWeathers = MutableStateFlow(listOf<CurrentLocationWeather>())
    val favouritePlacesWeathers = _favouritePlacesWeathers
        .onStart {
            getFavouritePlacesWeathers()
            delay(1000)
            checkUiState()
        }
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

    private suspend fun getFavouritePlacesWeathers() {
        _favouritePlacesWeathers.value = currentWeatherRepository.getFavouritePlacesWeathers(favouritePlaces)
    }

    private suspend fun getCurrentPlaceWeather() {
        _currentPlaceWeather.value = currentWeatherRepository.getCurrentWeather(currentPlace)
    }

    private fun checkUiState() {
        mainScreenUiState = if (_favouritePlacesWeathers.value.contains(CurrentLocationWeather())) {
            MainScreenUiState.LOADING
        } else if(_currentPlaceWeather.value == CurrentLocationWeather()) {
            MainScreenUiState.LOADING
        } else {
            MainScreenUiState.SUCCESS
        }
    }

    fun onPullToRefreshTrigger() {
        viewModelScope.launch {
            isConnected.collect { value ->
                if (value) {
                    _isRefreshing.update { true }
                    getCurrentPlaceWeather()
                    getFavouritePlacesWeathers()
                    _isRefreshing.update { false }
                } else {
                    _toastEvent.emit("No network connection")
                }
            }
        }
    }
}