package com.steven.developer.openweather.domain.usecase.forecast

import com.steven.developer.openweather.data.repository.ForecastRepositoryImpl
import com.steven.developer.openweather.model.*
import javax.inject.Inject

class GetForecastFromDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    fun getForecastFromDbUseCase() : Forecast? = forecastRepositoryImpl.getForecastWeather()
}