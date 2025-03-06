package com.steven.developer.openweather.data.repository

import com.steven.developer.openweather.data.local.profile.dao.UserDao
import com.steven.developer.openweather.data.local.weather.entity.UserEntity
import com.steven.developer.openweather.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun getUserByUsername(username: String): UserEntity? {
        return userDao.getUserByUsername(username)
    }

    override suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }
}
