package com.steven.developer.openweather.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.steven.developer.openweather.data.local.profile.dao.UserDao
import com.steven.developer.openweather.data.local.weather.dao.CityDao
import com.steven.developer.openweather.data.local.weather.dao.ForecastDao
import com.steven.developer.openweather.data.local.weather.entity.CityEntity
import com.steven.developer.openweather.data.local.weather.entity.ForecastEntity
import com.steven.developer.openweather.data.local.profile.entity.UserEntity

@Database(
    entities = [CityEntity::class, ForecastEntity::class, UserEntity::class],
    version = 2,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun forecastWeatherDao(): ForecastDao

    abstract fun userDao(): UserDao
}