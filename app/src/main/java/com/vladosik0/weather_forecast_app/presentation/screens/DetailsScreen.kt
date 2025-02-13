package com.vladosik0.weather_forecast_app.presentation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vladosik0.weather_forecast_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, cityName: String) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(id = R.string.app_name)) },
                  colors = TopAppBarDefaults.topAppBarColors(
                      containerColor = MaterialTheme.colorScheme.primary,
                      titleContentColor = MaterialTheme.colorScheme.onPrimary,
                  ),
                  navigationIcon = {
                      IconButton(onClick = { navController.navigateUp() }) {
                          Icon(
                              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                              contentDescription = "Return",
                              tint = MaterialTheme.colorScheme.onPrimary
                          )
                      }
                  })
    }) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(8.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ShortInfoItem(cityName, 5)
            DailyForecastItem()
            WeeklyForecastItem()
            SunStateItem()
            AdditionalInfoItem()
        }
    }
}

@Composable
fun ShortInfoItem(locationName: String, temperature: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = 300))
            .padding(6.dp)
            .size(112.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, bottom = 8.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(text = locationName, fontSize = 24.sp)
                Text(text = "16:08", fontSize = 14.sp, lineHeight = 14.sp)
                Text(text = "28.01.2025, Tue", fontSize = 14.sp, lineHeight = 14.sp)
            }
            Text(text = "$temperature°C", fontSize = 24.sp)
            Image(
                modifier = Modifier.size(56.dp),
                painter = painterResource(id = R.drawable._113),
                contentDescription = null
            )
        }
    }
}

@Composable
fun DailyForecastItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .size(154.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.size(24.dp))
                Icon(
                    painter = painterResource(R.drawable.clock),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.temperature),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.humidity),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.wind),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(12) {
                    HourlyForecastItem()
                }
            }
        }
    }
}

@Composable
fun HourlyForecastItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.drawable._113),
            contentDescription = null
        )
        Text(text = "14:00")
        Text(text = "8°C")
        Text(text = "6%")
        Text(text = "3 m/s")
    }
}

@Composable
fun WeeklyForecastItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .size(124.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.calendar),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(text = "Tue")
                Text(text = "Wed")
                Text(text = "Thu")
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(18.dp))
                Image(
                    modifier = Modifier.weight(1f),
                    painter = painterResource(id = R.drawable._113),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier.weight(1f),
                    painter = painterResource(id = R.drawable._113),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier.weight(1f),
                    painter = painterResource(id = R.drawable._113),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.humidity),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(text = "5%")
                Text(text = "6%")
                Text(text = "10%")
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.temperature),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(text = "8°C")
                Text(text = "9°C")
                Text(text = "10°C")

            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.temperature_feels_like),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(text = "8°C")
                Text(text = "9°C")
                Text(text = "10°C")
            }
        }
    }
}


@Composable
fun SunStateItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .size(124.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val sunriseComposition by rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(
                        R.raw.sunrise
                    )
                )
                val sunriseProgress by animateLottieCompositionAsState(
                    composition = sunriseComposition, iterations = LottieConstants.IterateForever
                )
                LottieAnimation(modifier = Modifier.size(64.dp),
                                composition = sunriseComposition,
                                progress = { sunriseProgress })
                Text(text = "Sunrise")
                Text(text = "07:23")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val sunsetComposition by rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(
                        R.raw.sunset
                    )
                )
                val sunsetProgress by animateLottieCompositionAsState(
                    composition = sunsetComposition, iterations = LottieConstants.IterateForever
                )
                LottieAnimation(modifier = Modifier.size(64.dp),
                                composition = sunsetComposition,
                                progress = { sunsetProgress })
                Text(text = "Sunset")
                Text(text = "16:27")
            }
        }
    }
}

@Composable
fun AdditionalInfoItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .size(132.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically)
            ) {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.air_quality),
                    contentDescription = null
                )
                Text(text = "Air Quality\nIndex(AQI)", textAlign = TextAlign.Center)
                Text(text = "Medium")
            }
            VerticalDivider(Modifier.width(1.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically)
            ) {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.precipitation),
                    contentDescription = null
                )
                Text(text = "Precipitation\nprobability", textAlign = TextAlign.Center)
                Text(text = "5%")
            }
            VerticalDivider(Modifier.width(1.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically)
            ) {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.wind),
                    contentDescription = null
                )
                Text(text = "Wind's\nspeed", textAlign = TextAlign.Center)
                Text(text = "8 km/h")
            }
        }
    }
}