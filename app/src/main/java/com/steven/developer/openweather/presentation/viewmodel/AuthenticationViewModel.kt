package com.steven.developer.openweather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steven.developer.openweather.data.local.weather.entity.UserEntity
import com.steven.developer.openweather.domain.usecase.LoginUseCase
import com.steven.developer.openweather.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val loginUseCase: LoginUseCase, private val registerUseCase: RegisterUseCase) : ViewModel() {
    fun login(username: String, passwordHash: String, onSuccess: (UserEntity) -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            val user = loginUseCase(username, passwordHash)
            if (user != null) {
                onSuccess(user)
            } else {
                onError()
            }
        }
    }

    fun register(user: UserEntity) {
        viewModelScope.launch {
            registerUseCase(user)
        }
    }
}