package com.example.weatherApp.feature.settings.publicApi.model

import androidx.annotation.StringRes
import com.example.weatherApp.feature.settings.publicApi.R

enum class WindSpeedUnit(@StringRes val stringRes: Int) {
    KilometersPerHour(R.string.feature_settings_public_api_wind_speed_km),
    MilesPerHour(R.string.feature_settings_public_api_wind_speed_mph),
    MetersPerSecond(R.string.feature_settings_public_api_wind_speed_meter),
    ;

    companion object {
        fun fromName(name: String): WindSpeedUnit =
            entries.firstOrNull { it.name == name } ?: KilometersPerHour
    }
}
