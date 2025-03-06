package com.steven.developer.openweather.data.mapper

import com.steven.developer.openweather.data.remote.entity.CityDto
import com.steven.developer.openweather.data.remote.entity.CloudinessDto
import com.steven.developer.openweather.data.remote.entity.CoordDto
import com.steven.developer.openweather.data.remote.entity.ForecastDto
import com.steven.developer.openweather.data.remote.entity.ForecastWeatherDto
import com.steven.developer.openweather.data.remote.entity.MainDto
import com.steven.developer.openweather.data.remote.entity.WeatherDto
import com.steven.developer.openweather.domain.mapper.IEntityMapper
import com.steven.developer.openweather.model.City
import com.steven.developer.openweather.model.Cloudiness
import com.steven.developer.openweather.model.CoordinateModel
import com.steven.developer.openweather.model.Forecast
import com.steven.developer.openweather.model.ForecastWeather
import com.steven.developer.openweather.model.Main
import com.steven.developer.openweather.model.Weather
import javax.inject.Inject

class ForecastDtoMapper @Inject constructor() : IEntityMapper<ForecastDto, Forecast> {
    override fun mapFromEntity(entity: ForecastDto): Forecast {
        val forecastWeather: List<ForecastWeather> = entity.weatherList.map {
            ForecastWeather(
                weatherData = Main(
                    it.weatherData.temp,
                    it.weatherData.feelsLike,
                    it.weatherData.pressure,
                    it.weatherData.humidity
                ),
                weatherStatus = listOf(
                    Weather(it.weatherStatus[0].mainDescription, it.weatherStatus[0].description)
                ),
                date = it.date,
                cloudiness = Cloudiness(it.cloudinessDto.cloudiness)
            )
        }

        return Forecast(
            forecastWeather,
            City(
                entity.cityDtoData.country,
                entity.cityDtoData.timezone,
                entity.cityDtoData.sunrise,
                entity.cityDtoData.sunset,
                entity.cityDtoData.cityName,
                CoordinateModel(
                    entity.cityDtoData.coordinate.latitude,
                    entity.cityDtoData.coordinate.longitude
                )
            )
        )
    }

    override fun entityFromModel(model: Forecast): ForecastDto {
        val forecastWeatherDto: List<ForecastWeatherDto> = model.weatherList.map {
            ForecastWeatherDto(
                MainDto(
                    it.weatherData.temp,
                    it.weatherData.feelsLike,
                    it.weatherData.pressure,
                    it.weatherData.humidity
                ),
                listOf(
                    WeatherDto(it.weatherStatus[0].mainDescription, it.weatherStatus[0].description)
                ),
                it.date,
                CloudinessDto(it.cloudiness.cloudiness)
            )
        }

        return ForecastDto(
            forecastWeatherDto,
            CityDto(
                model.cityDtoData.country,
                model.cityDtoData.timezone,
                model.cityDtoData.sunrise,
                model.cityDtoData.sunset,
                model.cityDtoData.cityName,
                CoordDto(
                    model.cityDtoData.coordinate.latitude,
                    model.cityDtoData.coordinate.longitude
                )
            )
        )
    }
}