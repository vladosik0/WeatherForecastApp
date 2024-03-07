package com.vladosik0.weather_forecast_app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vladosik0.weather_forecast_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                text = stringResource(id = R.string.current_location_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(6.dp)
            )
            HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(6.dp))
            LocationItem(locationName = "Kyiv", temperature = 5)
            Text(
                text = stringResource(id = R.string.favourite_places_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(6.dp)
            )
            HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(6.dp))
            LocationItem(locationName = "London", temperature = 2)
            LocationItem(locationName = "Kyiv", temperature = 5)
            LocationItem(locationName = "New York", temperature = 7)
            LocationItem(locationName = "Madrid", temperature = 8)
            LocationItem(locationName = "Amsterdam", temperature = 10)
            LocationItem(locationName = "Lisbon", temperature = 3)
            LocationItem(locationName = "Munich", temperature = -2)
        }
    }
}

@Composable
fun LocationItem(
    locationName: String,
    temperature: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(6.dp)
            .size(96.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = CardDefaults.elevatedShape
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = locationName,
                modifier = Modifier.weight(1f),
                fontSize = 24.sp
            )
            Text(
                text = "$temperature°C",
                fontSize = 24.sp
            )
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = R.drawable._113),
                contentDescription = null
            )
        }
    }

}