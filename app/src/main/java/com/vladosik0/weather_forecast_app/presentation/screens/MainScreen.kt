package com.vladosik0.weather_forecast_app.presentation.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vladosik0.weather_forecast_app.R
import com.vladosik0.weather_forecast_app.domain.data_serialization.CurrentLocationWeather
import com.vladosik0.weather_forecast_app.presentation.PullToRefreshBox
import com.vladosik0.weather_forecast_app.presentation.navigation.NavigationRoutes

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    currentPlaceWeather: CurrentLocationWeather,
    favouritePlacesWeathers: List<CurrentLocationWeather>,
    isRefreshing: Boolean,
    toastMessage: String,
    onRefresh: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(id = R.string.app_name)) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        )
    }) {
        val context = LocalContext.current
        showToast(context, toastMessage)
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = {
                onRefresh()
                showToast(context, toastMessage)
            },
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(6.dp)
            ) {
                stickyHeader {
                    Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
                        Text(
                            text = stringResource(id = R.string.current_location_title),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(6.dp)
                        )
                        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(6.dp))
                        LocationItem(locationWeather = currentPlaceWeather, onClick = {
                            navController.navigate(
                                NavigationRoutes.detailsScreen + "/${currentPlaceWeather.location.name}"
                            )
                        })
                        Text(
                            text = stringResource(id = R.string.favourite_places_title),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(6.dp)
                        )
                        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(6.dp))
                    }
                }
                items(favouritePlacesWeathers) { item ->
                    LocationItem(locationWeather = item, onClick = {
                        navController.navigate(
                            NavigationRoutes.detailsScreen + "/${currentPlaceWeather.location.name}"
                        )
                    })
                }
            }
        }
    }
}

@Composable
fun LocationItem(
    locationWeather: CurrentLocationWeather,
    onClick: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .size(96.dp),
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
                Text(text = locationWeather.location.name, fontSize = 24.sp)
                Text(text = locationWeather.location.localTime, fontSize = 14.sp, lineHeight = 14.sp)
                Text(text = locationWeather.location.localDate, fontSize = 14.sp, lineHeight = 14.sp)
            }
            Text(
                text = "${locationWeather.current.temperatureInCelsius}°C", fontSize = 24.sp
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(locationWeather.current.condition.icon).crossfade(true).build(),
                modifier = Modifier.size(56.dp),
                contentDescription = locationWeather.current.condition.text
            )
        }
    }
}

fun showToast(context: Context, toastMessage: String) {
    if(toastMessage.isNotEmpty()) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    }
}
