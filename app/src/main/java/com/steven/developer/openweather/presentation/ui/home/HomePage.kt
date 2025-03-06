package com.steven.developer.openweather.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.steven.developer.openweather.R
import com.steven.developer.openweather.model.Forecast
import com.steven.developer.openweather.presentation.viewmodel.HomeViewModel
import com.steven.developer.openweather.utils.AppStrings

@Composable
fun HomePage(homeViewModel: HomeViewModel) {
    val homeCurrentWeatherState by homeViewModel.homeForecastState.collectAsState()

    Log.d("TAG", "HomePage: ${homeCurrentWeatherState.toString()} ")
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        BackgroundImage()
        Column(Modifier.padding(innerPadding)) {
            when (homeCurrentWeatherState) {
                is HomeState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                     //   CircularProgressBar(modifier = Modifier.size(LocalConfiguration.current.screenWidthDp.dp / 3))
                    }
                }
                is HomeState.Success -> {
                    if ((homeCurrentWeatherState as HomeState.Success).forecast != null) {
                        (homeCurrentWeatherState as HomeState.Success).forecast?.let { CurrentWeatherSection(it) }
                    }
                }
                is HomeState.Error -> {

                }
            }
        }
    }

}

@Composable
private fun BackgroundImage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CurrentWeatherSection(todayWeather: Forecast) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 72.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = todayWeather.cityDtoData.cityName,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontSize = 20.sp
        )
        Text(
            text = "${todayWeather.weatherList[0].weatherData.temp.toInt()}${AppStrings.degree}",
            color = Color.White,
            fontSize = 30.sp
        )
        Text(
            text = todayWeather.weatherList[0].weatherStatus[0].description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            text = "H:${todayWeather.cityDtoData.coordinate.longitude}°  L:${todayWeather.cityDtoData.coordinate.latitude}°",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}
