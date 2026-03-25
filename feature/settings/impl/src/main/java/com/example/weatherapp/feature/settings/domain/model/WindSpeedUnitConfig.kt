package com.example.weatherapp.feature.settings.domain.model

import androidx.annotation.StringRes
import com.example.weatherApp.feature.settings.publicApi.model.WindSpeedUnit

data class WindSpeedUnitConfig(
    @StringRes val windSpeedUnitStringRes: Int,
    val windSpeedUnit: WindSpeedUnit,
    val isSelected: Boolean = false,
)
