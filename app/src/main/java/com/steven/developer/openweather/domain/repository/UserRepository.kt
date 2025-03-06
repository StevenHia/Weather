package com.steven.developer.openweather.domain.repository

import com.steven.developer.openweather.data.local.weather.entity.UserEntity

interface UserRepository {
    suspend fun insertUser(user: UserEntity)
    suspend fun getUserByUsername(username: String):UserEntity?
    suspend fun updateUser(user: UserEntity)
}