package com.steven.developer.openweather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.steven.developer.openweather.domain.usecase.forecast.AddCityToDbUseCase
import com.steven.developer.openweather.domain.usecase.forecast.AddForecastToDbUseCase
import com.steven.developer.openweather.domain.usecase.forecast.GetForecastFromDbUseCase
import com.steven.developer.openweather.domain.usecase.forecast.GetForecastUseCase
import com.steven.developer.openweather.domain.usecase.forecast.UpdateCityDbUseCase
import com.steven.developer.openweather.domain.usecase.forecast.UpdateForecastDbUseCase
import com.steven.developer.openweather.domain.usecase.location.GetLocationUseCase
import com.steven.developer.openweather.model.City
import com.steven.developer.openweather.model.Forecast
import com.steven.developer.openweather.presentation.ui.screen.home.HomeState
import com.steven.developer.openweather.utils.ExceptionTitles
import com.steven.developer.openweather.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addForecastDb: AddForecastToDbUseCase,
    private val addCityDb: AddCityToDbUseCase,
    private val updateCityDbUseCase: UpdateCityDbUseCase,
    private val getForecastDb: GetForecastFromDbUseCase,
    private val updateForecastDb: UpdateForecastDbUseCase,
    private val forecastUseCase: GetForecastUseCase,
    private val currentLocationUseCase: GetLocationUseCase,
) : ViewModel() {

    private val _homeForecastState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeForecastState = _homeForecastState.asStateFlow()


    fun loadLocation() {
        _homeForecastState.value = HomeState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val locationData = currentLocationUseCase.getLocation()
                if (locationData != null) {
                    fetchForecast(locationData.latitude, locationData.longitude)
                } else if (isForecastCached()) {
                    getCachedForecast()
                } else {
                    _homeForecastState.value =
                        HomeState.Error(ExceptionTitles.NO_INTERNET_CONNECTION)
                }
            } catch (e: Exception) {
                if (isForecastCached()) {
                    getCachedForecast()
                } else {
                    _homeForecastState.value = HomeState.Error(e.message)
                }
            }
        }
    }

    private suspend fun fetchForecast(latitude: Double, longitude: Double) {
        when (val result = forecastUseCase.getForecast(latitude, longitude)) {
            is Resource.Success -> {
                _homeForecastState.value = HomeState.Success(result.data)
                if (result.data != null) {
                    if (!isForecastCached()) {
                        cacheForecast(result.data, result.data.cityDtoData)
                    } else {
                        updateCachedForecast(result.data, result.data.cityDtoData)
                    }
                }
            }

            is Resource.Error -> {
                _homeForecastState.value = HomeState.Error(result.message)
            }
            else -> {}
        }
    }

    private suspend fun cacheForecast(forecast: Forecast, city: City) {
        addForecastDb.addForecastToDbUseCase(
            forecast,
            forecast.weatherList.size
        )
        addCityDb.addCityDb(city)
    }

    private suspend fun updateCachedForecast(forecast: Forecast, city: City) {
        updateForecastDb.updateForecastDbUseCase(
            forecast,
            forecast.weatherList.size
        )
        updateCityDbUseCase.updateCityDb(city)
    }

    // Data cannot be null.
    // Because before this function is called, it is checked for null with the isForecastCached() function.
    private fun getCachedForecast() {
        _homeForecastState.value =
            HomeState.Success(getForecastDb.getForecastFromDbUseCase())
    }

    private fun isForecastCached(): Boolean {
        return getForecastDb.getForecastFromDbUseCase() != null
    }

}