package com.steven.developer.openweather.data.remote.entity

import com.google.gson.annotations.SerializedName

data class SysDto(
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long,
)