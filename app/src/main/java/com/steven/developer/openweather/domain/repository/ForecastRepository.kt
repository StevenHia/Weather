package com.steven.developer.openweather.domain.repository

import com.steven.developer.openweather.model.City
import com.steven.developer.openweather.model.Forecast
import com.steven.developer.openweather.utils.Resource


interface ForecastRepository {
    suspend fun getForecastData(latitude: Double, longitude: Double ): Resource<Forecast>

    suspend fun addForecastWeather(forecast: Forecast)

    suspend fun addCity(city: City)

    fun getForecastWeather() : Forecast?

    fun getCity() : City

    suspend fun updateForecastWeather(forecast: Forecast)

    suspend fun updateCity(city: City)
}