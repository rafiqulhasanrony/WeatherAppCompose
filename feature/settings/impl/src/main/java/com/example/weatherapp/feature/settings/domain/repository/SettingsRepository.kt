package com.example.weatherapp.feature.settings.domain.repository

import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun isDynamicThemeEnabled(): Flow<Boolean>
    fun getSelectedTheme(): Flow<ThemeType>
    fun getSelectedMeasurementUnit(): Flow<UnitOfMeasurement>

    suspend fun updateDynamicTheme(isEnabled: Boolean)
    suspend fun saveThemeType(themeType: ThemeType)
    suspend fun saveMeasurementUnitType(unit: UnitOfMeasurement)
}
