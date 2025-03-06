package com.steven.developer.openweather.domain.usecase

import com.steven.developer.openweather.data.local.weather.entity.UserEntity
import com.steven.developer.openweather.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(username: String, passwordHash: String): UserEntity? {
        val user = userRepository.getUserByUsername(username)
        return if (user != null && user.userPassword == passwordHash) user else null
    }
}

class RegisterUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: UserEntity) {
        userRepository.insertUser(user)
    }
}

class GetUserByUsernameUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke(username: String) : UserEntity? {
        return userRepository.getUserByUsername(username)
    }
}
class UpdateUserUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke(user: UserEntity) : Unit{
        return userRepository.updateUser(user)
    }
}