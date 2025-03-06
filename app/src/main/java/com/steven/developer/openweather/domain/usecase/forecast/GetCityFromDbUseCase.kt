package com.steven.developer.openweather.domain.usecase.forecast

import com.steven.developer.openweather.data.repository.ForecastRepositoryImpl
import javax.inject.Inject

class GetCityFromDbUseCase @Inject constructor(private val forecastRepositoryImpl: ForecastRepositoryImpl) {
    fun getCityFromDb() = forecastRepositoryImpl.getCity()
}