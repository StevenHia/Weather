package com.steven.developer.openweather.data.remote

import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(private val api: WeatherApiService) {
    suspend fun getForecastData(latitude: Double, longitude: Double) =
        api.getForecastData(latitude, longitude)
}