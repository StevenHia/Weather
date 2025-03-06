package com.steven.developer.openweather.data.mapper

import com.steven.developer.openweather.data.local.weather.entity.CityEntity
import com.steven.developer.openweather.data.local.weather.entity.ForecastEntity
import com.steven.developer.openweather.model.City
import com.steven.developer.openweather.model.Cloudiness
import com.steven.developer.openweather.model.CoordinateModel
import com.steven.developer.openweather.model.Forecast
import com.steven.developer.openweather.model.ForecastWeather
import com.steven.developer.openweather.model.Main
import com.steven.developer.openweather.model.Weather
import javax.inject.Inject

class ForecastEntityMapper @Inject constructor() {
    fun mapFromEntity(entityForecast: List<ForecastEntity>, entityCity: CityEntity): Forecast {
        return Forecast(
            entityForecast.map {
                ForecastWeather(
                    it.id,
                    Main(
                        it.temp,
                        it.feels_like,
                        it.pressure,
                        it.humidity
                    ),
                    listOf(
                        Weather(it.mainDescription, it.description)
                    ),
                    it.date,
                    Cloudiness(it.cloudiness)
                )
            },
            City(
                entityCity.country,
                entityCity.timezone,
                entityCity.sunrise,
                entityCity.sunset,
                entityCity.cityName,
                CoordinateModel(
                    entityCity.longitude,
                    entityCity.latitude
                )
            )
        )
    }

    fun entityFromModel(model: Forecast): ForecastEntity {
        return ForecastEntity(
            id = model.weatherList[0].id,
            temp = model.weatherList[0].weatherData.temp,
            feels_like = model.weatherList[0].weatherData.feelsLike,
            pressure = model.weatherList[0].weatherData.pressure,
            humidity = model.weatherList[0].weatherData.humidity,
            description = model.weatherList[0].weatherStatus[0].description,
            mainDescription = model.weatherList[0].weatherStatus[0].mainDescription,
            date = model.weatherList[0].date,
            cloudiness = model.weatherList[0].cloudiness.cloudiness
        )
    }
}