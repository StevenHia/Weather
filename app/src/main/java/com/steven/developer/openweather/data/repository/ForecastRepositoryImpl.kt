package com.steven.developer.openweather.data.repository

import com.steven.developer.openweather.utils.Resource
import com.steven.developer.openweather.data.local.weather.CityLocalDataSource
import com.steven.developer.openweather.data.local.weather.ForecastLocalDataSource
import com.steven.developer.openweather.data.remote.ForecastRemoteDataSource
import com.steven.developer.openweather.data.mapper.CityEntityMapper
import com.steven.developer.openweather.data.mapper.ForecastDtoMapper
import com.steven.developer.openweather.data.mapper.ForecastEntityMapper
import com.steven.developer.openweather.domain.repository.ForecastRepository
import com.steven.developer.openweather.model.*
import com.steven.developer.openweather.utils.Constants
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val forecastLocalDataSource: ForecastLocalDataSource,
    private val cityLocalDataSource: CityLocalDataSource,
    private val dtoMapper: ForecastDtoMapper,
    private val entityMapper: ForecastEntityMapper,
    private val cityEntityMapper: CityEntityMapper
) : ForecastRepository {
    override suspend fun getForecastData(
        latitude: Double,
        longitude: Double
    ): Resource<Forecast> {
        return try {
            Resource.Success(
                dtoMapper.mapFromEntity(
                    forecastRemoteDataSource.getForecastData(
                        latitude,
                        longitude
                    )
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }

    override suspend fun addForecastWeather(forecast: Forecast) {
        forecastLocalDataSource.addForecastWeather(
            entityMapper.entityFromModel(forecast)
        )
    }

    override suspend fun addCity(city: City) {
        forecastLocalDataSource.addCity(
            cityEntityMapper.entityFromModel(city)
        )
    }

    override fun getForecastWeather(): Forecast? {
        return if (forecastLocalDataSource.getForecastWeather().isEmpty()) {
            null
        } else {
            entityMapper.mapFromEntity(
                forecastLocalDataSource.getForecastWeather(),
                cityLocalDataSource.getCity()
            )
        }
    }

    override fun getCity(): City {
        return cityEntityMapper.mapFromEntity(forecastLocalDataSource.getCity())
    }

    override suspend fun updateForecastWeather(forecast: Forecast) {
        forecastLocalDataSource.updateForecastWeather(
            entityMapper.entityFromModel(forecast)
        )
    }

    override suspend fun updateCity(city: City) {
        forecastLocalDataSource.updateCity(
            cityEntityMapper.entityFromModel(city)
        )
    }
}