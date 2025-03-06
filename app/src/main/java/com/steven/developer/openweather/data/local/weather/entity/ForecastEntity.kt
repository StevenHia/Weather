package com.steven.developer.openweather.data.local.weather.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.steven.developer.openweather.utils.Database

@Entity(tableName = Database.forecast_table)
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "temp")
    var temp: Double,

    @ColumnInfo(name = "feels_like")
    var feels_like: Double,

    @ColumnInfo(name = "pressure")
    var pressure: Double,

    @ColumnInfo(name = "humidity")
    var humidity: Int,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "main_description")
    var mainDescription: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "cloudinessDto")
    val cloudiness: Int,
)
