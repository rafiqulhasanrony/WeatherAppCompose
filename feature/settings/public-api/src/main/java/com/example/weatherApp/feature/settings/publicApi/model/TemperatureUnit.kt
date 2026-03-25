package com.example.weatherApp.feature.settings.publicApi.model

import androidx.annotation.StringRes
import com.example.weatherApp.feature.settings.publicApi.R

enum class TemperatureUnit(@StringRes val stringRes: Int) {
    Celsius(R.string.feature_settings_public_api_temperature_celsius),
    Fahrenheit(R.string.feature_settings_public_api_temperature_fahrenheit),
    ;

    companion object {
        fun fromName(name: String): TemperatureUnit =
            entries.firstOrNull { it.name == name } ?: Celsius
    }
}
