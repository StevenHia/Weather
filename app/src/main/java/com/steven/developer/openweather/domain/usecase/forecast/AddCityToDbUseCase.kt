package com.steven.developer.openweather.domain.usecase.forecast

import com.steven.developer.openweather.data.repository.ForecastRepositoryImpl
import com.steven.developer.openweather.model.City
import javax.inject.Inject

class AddCityToDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    suspend fun addCityDb(city: City) = forecastRepositoryImpl.addCity(city)
}