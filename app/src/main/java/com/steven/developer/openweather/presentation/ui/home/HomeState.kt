package com.steven.developer.openweather.presentation.ui.home

import com.steven.developer.openweather.model.*


sealed interface HomeState {
    data class Success(val forecast: Forecast?): HomeState
    data class Error(val errorMessage: String?): HomeState

    data object Loading: HomeState
}