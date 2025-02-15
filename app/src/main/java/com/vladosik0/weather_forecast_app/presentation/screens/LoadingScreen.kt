package com.vladosik0.weather_forecast_app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.vladosik0.weather_forecast_app.R
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize().background(color = colorResource(R.color.weather_blue))
    ) {
        val weatherComposition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(
                R.raw.weather
            )
        )
        val weatherProgress by animateLottieCompositionAsState(
            composition = weatherComposition, iterations = LottieConstants.IterateForever
        )
        LottieAnimation(modifier = Modifier.size(128.dp),
                        composition = weatherComposition,
                        progress = { weatherProgress })
    }
}