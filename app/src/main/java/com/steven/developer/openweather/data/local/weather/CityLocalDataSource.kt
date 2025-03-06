package com.steven.developer.openweather.data.local.weather

import com.steven.developer.openweather.data.local.weather.dao.CityDao
import com.steven.developer.openweather.data.local.weather.entity.CityEntity
import javax.inject.Inject

class CityLocalDataSource @Inject constructor(private val cityDao: CityDao) {

    suspend fun addCity(cityEntity: CityEntity) =
        cityDao.addCity(cityEntity)

    fun getCity() = cityDao.getCity()

    suspend fun updateCity(cityEntity: CityEntity) =
        cityDao.updateCity(cityEntity)
}