package com.example.weatherapp.feature.settings.domain.preference

import com.example.weatherApp.feature.settings.publicApi.model.TemperatureUnit
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.WindSpeedUnit
import kotlinx.coroutines.flow.Flow

interface SettingsPreferenceDataSource {
    fun isDynamicThemeEnabled(): Flow<Boolean>
    fun getTheme(): Flow<ThemeType>
    fun getTemperatureUnit(): Flow<TemperatureUnit>
    fun getWindSpeedUnit(): Flow<WindSpeedUnit>

    suspend fun saveDynamicTheme(isEnabled: Boolean)
    suspend fun saveTheme(themeType: ThemeType)
    suspend fun saveTemperatureUnit(unit: TemperatureUnit)
    suspend fun saveWindSpeedUnit(unit: WindSpeedUnit)
}
