package com.example.weatherapp.feature.settings.domain.model

import androidx.annotation.StringRes
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType

data class ThemeConfig(
    @StringRes val themeTypeStringRes: Int,
    val themeType: ThemeType,
    val isSelected: Boolean = false,
)
