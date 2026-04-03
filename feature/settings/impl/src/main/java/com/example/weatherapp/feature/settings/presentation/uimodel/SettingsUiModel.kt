package com.example.weatherapp.feature.settings.presentation.uimodel

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsUiModel(
    val isDynamicThemeEnabled: Boolean = false,
    val themeConfigUiModel: ThemeConfigUiModel = ThemeConfigUiModel(),
    val unitOfMeasurementConfigUiModel: UnitOfMeasurementConfigUiModel = UnitOfMeasurementConfigUiModel(),
)
