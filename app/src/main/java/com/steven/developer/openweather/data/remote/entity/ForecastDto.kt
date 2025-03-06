package com.steven.developer.openweather.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("list") val weatherList: List<ForecastWeatherDto>,
    @SerializedName("city") val cityDtoData: CityDto
)
