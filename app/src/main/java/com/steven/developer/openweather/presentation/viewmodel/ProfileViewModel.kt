package com.steven.developer.openweather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steven.developer.openweather.data.local.weather.entity.UserEntity
import com.steven.developer.openweather.domain.usecase.GetUserByUsernameUseCase
import com.steven.developer.openweather.domain.usecase.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserByUsernameUseCase: GetUserByUsernameUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {
    private val _profileState = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val profileState = _profileState.asStateFlow()

    fun loadProfile(userId: Int) {
        _profileState.value = ProfileState.Loading
        viewModelScope.launch {
            // Assuming you have a way to map userId to username
            // For this example, let's say it's just an index
            val user =
                getUserByUsernameUseCase("user")
            if (user != null) {
                _profileState.value = ProfileState.Success(user)
            } else {
                _profileState.value = ProfileState.Error("User not found")
            }
        }
    }

    fun updateProfile(user: UserEntity) {
        viewModelScope.launch {
            updateUserUseCase(user)
            _profileState.value = ProfileState.Success(user)
        }
    }
}

sealed class ProfileState {
    data object Loading : ProfileState()
    data class Success(val user: UserEntity) : ProfileState()
    data class Error(val message: String) : ProfileState()
}