package com.steven.developer.openweather.domain.usecase

import com.steven.developer.openweather.data.local.profile.entity.UserEntity
import com.steven.developer.openweather.domain.repository.UserRepository
import com.steven.developer.openweather.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(userName: String, passwordHash: String): UserEntity? {
        val user = userRepository.login(userName,passwordHash)
        return if (user != null && user.userPassword == passwordHash) user else null
    }
}

class RegisterUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: UserEntity) {
        userRepository.insertUser(user)
    }
}

class GetUserByIdUseCase @Inject constructor(private val userRepository: UserRepository){

    operator fun invoke(userId: Int): Flow<Resource<UserEntity>> = flow {
        try {
            emit(Resource.Loading())
            val user = userRepository.getUserById(userId)
            if (user != null) {
                emit(Resource.Success(user))
            } else {
                emit(Resource.Error("User not found"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error"))
        }
    }
}

class UpdateUserUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke(user: UserEntity) {
        return userRepository.updateUser(user)
    }
}