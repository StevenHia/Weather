package com.steven.developer.openweather.data.remote

import com.steven.developer.openweather.data.remote.entity.ForecastDto
import com.steven.developer.openweather.utils.NetworkService
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET(NetworkService.FORECAST_END_POINT)
    suspend fun getForecastData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String = NetworkService.API_KEY,
        @Query("units") units: String = NetworkService.UNITS,
    ): ForecastDto
}