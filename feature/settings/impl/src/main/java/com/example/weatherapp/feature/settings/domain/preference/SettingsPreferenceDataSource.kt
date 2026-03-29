package com.example.weatherapp.feature.settings.domain.preference

import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import kotlinx.coroutines.flow.Flow

interface SettingsPreferenceDataSource {
    fun isDynamicThemeEnabled(): Flow<Boolean>
    fun getSelectedTheme(): Flow<ThemeType>
    fun getSelectedMeasurementUnit(): Flow<UnitOfMeasurement>

    suspend fun saveDynamicTheme(isEnabled: Boolean)
    suspend fun saveTheme(themeType: ThemeType)
    suspend fun saveMeasurementUnit(unitOfMeasurement: UnitOfMeasurement)
}
