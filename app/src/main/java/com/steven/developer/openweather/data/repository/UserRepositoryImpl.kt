package com.steven.developer.openweather.data.repository

import com.steven.developer.openweather.data.local.profile.dao.UserDao
import com.steven.developer.openweather.data.local.profile.entity.UserEntity
import com.steven.developer.openweather.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun getUserById(userId: Int): UserEntity? {
        return userDao.getUserById(userId)
    }

    override suspend fun login(userName: String, password: String): UserEntity? {
        return userDao.login(userName, password)
    }

    override suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }
}
