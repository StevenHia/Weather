package com.steven.developer.openweather.data.local.weather.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.steven.developer.openweather.data.local.weather.entity.MyCityEntity
import com.steven.developer.openweather.utils.Database

@Dao
interface MyCityDao {
    @Insert
    suspend fun addCity(myCityEntity: MyCityEntity)

    @Query("SELECT * FROM ${Database.my_city_table}")
    fun getMyCity(): List<MyCityEntity>

    @Query("DELETE FROM ${Database.my_city_table} WHERE city = :cityName")
    fun deleteMyCity(cityName: String)

    @Query("UPDATE ${Database.my_city_table} SET `temp` = :temp, latitude = :latitude, longitude = :longitude, description = :description, weather_image = :weatherImage WHERE city = :cityName")
    suspend fun updateMyCity(
        temp: Double,
        latitude: Double,
        longitude: Double,
        cityName: String,
        description: String,
        weatherImage: Int
    )

    @Query("SELECT * FROM ${Database.my_city_table} WHERE city = :cityName")
    suspend fun getSpecificCity(cityName: String) : MyCityEntity?
}