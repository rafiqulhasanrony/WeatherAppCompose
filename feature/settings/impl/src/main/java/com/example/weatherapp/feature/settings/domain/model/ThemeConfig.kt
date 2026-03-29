package com.example.weatherapp.feature.settings.domain.model

import com.example.weatherApp.feature.settings.publicApi.model.ThemeType

data class ThemeConfig(
    val themeType: ThemeType,
    val isSelected: Boolean = false,
)
