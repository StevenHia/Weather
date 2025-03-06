package com.steven.developer.openweather.model

data class Forecast(
    val weatherList: List<ForecastWeather>,
    val cityDtoData: City
)

data class ForecastWeather(
    val id: Int = 1,
    val weatherData: Main,
    val weatherStatus: List<Weather>,
    val date: String,
    val cloudiness: Cloudiness
)


data class Main(
    val temp: Double,
    val feelsLike: Double,
    val pressure: Double,
    val humidity: Int,
)

data class Weather(
    val mainDescription: String,
    val description: String
)

data class Cloudiness(
    val cloudiness: Int
)
