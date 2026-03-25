package com.example.weatherApp.feature.settings.publicApi.model

import androidx.annotation.StringRes
import com.example.weatherApp.feature.settings.publicApi.R

enum class ThemeType(@StringRes val stringRes: Int) {
    Dark(R.string.feature_settings_public_api_theme_dark),
    Light(R.string.feature_settings_public_api_theme_light),
    System(R.string.feature_settings_public_api_theme_system);

    companion object {
        fun fromName(name: String): ThemeType =
            entries.firstOrNull { it.name == name } ?: System
    }
}
