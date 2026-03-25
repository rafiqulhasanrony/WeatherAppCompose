package com.example.weatherapp.feature.settings.domain.model

import androidx.annotation.StringRes
import com.example.weatherApp.feature.settings.publicApi.model.TemperatureUnit

data class TemperatureUnitConfig(
    @StringRes val temperatureUnitStringRes: Int,
    val temperatureUnit: TemperatureUnit,
    val isSelected: Boolean = false,
)
