package com.vladosik0.weather_forecast_app.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vladosik0.weather_forecast_app.presentation.screens.DetailsScreen
import com.vladosik0.weather_forecast_app.presentation.screens.LoadingScreen
import com.vladosik0.weather_forecast_app.presentation.screens.MainScreen
import com.vladosik0.weather_forecast_app.presentation.view_models.AppViewModelProvider
import com.vladosik0.weather_forecast_app.presentation.view_models.MainScreenUiState
import com.vladosik0.weather_forecast_app.presentation.view_models.MainScreenViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherForecastApp() {
    val navController = rememberAnimatedNavController()
    NavHost(navController = navController, startDestination = NavigationRoutes.mainScreen, builder = {
        composable(
            route = NavigationRoutes.mainScreen,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300)) }
        ) {
            val mainScreenViewModel = viewModel<MainScreenViewModel>(factory = AppViewModelProvider.Factory)
            val currentPlaceWeather by mainScreenViewModel.currentPlaceWeather.collectAsStateWithLifecycle()
            val favoritePlacesWeathers by mainScreenViewModel.favouritePlacesWeathers.collectAsStateWithLifecycle()
            val isRefreshing by mainScreenViewModel.isRefreshing.collectAsStateWithLifecycle()
            val toastMessage by mainScreenViewModel.toastEvent.collectAsStateWithLifecycle(initialValue = "")
            val mainScreenUiState = mainScreenViewModel.mainScreenUiState
            when(mainScreenUiState) {
                MainScreenUiState.SUCCESS -> MainScreen(
                    navController,
                    currentPlaceWeather,
                    favoritePlacesWeathers,
                    isRefreshing,
                    toastMessage,
                    mainScreenViewModel::onPullToRefreshTrigger
                )
                else -> LoadingScreen()
            }
        }
        composable(
            route = NavigationRoutes.detailsScreen + "/{cityName}",
            enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300)) }
        ) {
            val cityName = it.arguments?.getString("cityName")
            DetailsScreen(navController, cityName?:"Undefined loc")
        }
    })
}