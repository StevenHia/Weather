package com.steven.developer.openweather.data.local.weather

import com.steven.developer.openweather.data.local.weather.dao.CityDao
import com.steven.developer.openweather.data.local.weather.dao.ForecastDao
import com.steven.developer.openweather.data.local.weather.entity.CityEntity
import com.steven.developer.openweather.data.local.weather.entity.ForecastEntity
import javax.inject.Inject

class ForecastLocalDataSource @Inject constructor(
    private val forecastDao: ForecastDao,
    private val cityDao: CityDao
) {

    suspend fun addForecastWeather(forecastEntity: ForecastEntity) =
        forecastDao.addForecastWeather(forecastEntity)

    suspend fun addCity(cityEntity: CityEntity) =
        cityDao.addCity(cityEntity)

    fun getForecastWeather() = forecastDao.getForecastWeather()
    fun getCity() = cityDao.getCity()

    suspend fun updateForecastWeather(forecastEntity: ForecastEntity) =
        forecastDao.updateForecastWeather(forecastEntity)

    suspend fun updateCity(cityEntity: CityEntity) =
        cityDao.updateCity(cityEntity)
}