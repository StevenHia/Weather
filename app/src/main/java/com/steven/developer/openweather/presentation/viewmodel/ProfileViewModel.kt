package com.steven.developer.openweather.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steven.developer.openweather.data.local.profile.entity.UserEntity
import com.steven.developer.openweather.domain.usecase.GetUserByIdUseCase
import com.steven.developer.openweather.domain.usecase.UpdateUserUseCase
import com.steven.developer.openweather.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserByIdUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {
    private var _profileState = mutableStateOf<Resource<UserEntity>>(Resource.StandBy())
    val profileState: State<Resource<UserEntity>> = _profileState

    fun loadProfile(userId: Int) = viewModelScope.launch {
        viewModelScope.launch {
            getUserUseCase(userId).collect { resource ->
                _profileState.value = resource
            }
        }
    }

    fun updateProfile(user: UserEntity) {
        viewModelScope.launch {
            updateUserUseCase(user)
            _profileState.value = Resource.Success(user)
        }
    }
}