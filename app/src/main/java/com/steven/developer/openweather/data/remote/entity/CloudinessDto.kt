package com.steven.developer.openweather.data.remote.entity

import com.google.gson.annotations.SerializedName

data class CloudinessDto(
    @SerializedName("all") val cloudiness: Int
)