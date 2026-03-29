package com.example.weatherapp.feature.settings.domain.model

import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement

data class UnitOfMeasurementConfig(
    val unitOfMeasurement: UnitOfMeasurement,
    val isSelected: Boolean = false,
)
