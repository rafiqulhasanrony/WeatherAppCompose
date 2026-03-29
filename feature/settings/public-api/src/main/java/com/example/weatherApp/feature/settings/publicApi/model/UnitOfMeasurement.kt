package com.example.weatherApp.feature.settings.publicApi.model

import androidx.annotation.StringRes
import com.example.weatherApp.feature.settings.publicApi.R

enum class UnitOfMeasurement(@StringRes val stringRes: Int) {
    Metric(R.string.feature_settings_public_api_measurement_unit_metric),
    Imperial(R.string.feature_settings_public_api_measurement_unit_imperial),
    ;

    companion object {
        fun fromName(name: String): UnitOfMeasurement =
            entries.firstOrNull { it.name == name } ?: Metric
    }
}
