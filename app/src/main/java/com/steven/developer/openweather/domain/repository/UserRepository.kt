package com.steven.developer.openweather.domain.repository

import com.steven.developer.openweather.data.local.profile.entity.UserEntity

interface UserRepository {
    suspend fun login(userName: String, password: String): UserEntity?
    suspend fun insertUser(user: UserEntity)
    suspend fun getUserById(userId: Int): UserEntity?
    suspend fun updateUser(user: UserEntity)
}