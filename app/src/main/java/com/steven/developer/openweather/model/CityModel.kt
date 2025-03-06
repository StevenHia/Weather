package com.steven.developer.openweather.model

data class City(
    val country: String,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long,
    val cityName: String,
    val coordinate: CoordinateModel
)