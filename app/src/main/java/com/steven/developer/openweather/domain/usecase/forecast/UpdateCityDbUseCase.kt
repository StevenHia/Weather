package com.steven.developer.openweather.domain.usecase.forecast

import com.steven.developer.openweather.data.repository.ForecastRepositoryImpl
import com.steven.developer.openweather.model.*
import javax.inject.Inject

class UpdateCityDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun updateCityDb(city: City) = forecastRepositoryImpl.updateCity(city)
}