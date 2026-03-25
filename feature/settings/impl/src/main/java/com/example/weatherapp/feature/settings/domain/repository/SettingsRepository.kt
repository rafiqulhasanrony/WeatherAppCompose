package com.example.weatherapp.feature.settings.domain.repository

import com.example.weatherApp.feature.settings.publicApi.model.TemperatureUnit
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.WindSpeedUnit
import com.example.weatherapp.feature.settings.domain.model.TemperatureUnitConfig
import com.example.weatherapp.feature.settings.domain.model.ThemeConfig
import com.example.weatherapp.feature.settings.domain.model.WindSpeedUnitConfig
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun isDynamicThemeEnabled(): Flow<Boolean>
    suspend fun getAvailableThemeConfig(): Flow<List<ThemeConfig>>
    suspend fun getTemperatureUnitConfig(): Flow<List<TemperatureUnitConfig>>
    suspend fun getWindSpeedUnitConfig(): Flow<List<WindSpeedUnitConfig>>

    suspend fun updateDynamicTheme(isEnabled: Boolean)
    suspend fun saveThemeConfig(themeType: ThemeType)
    suspend fun saveTemperatureUnitConfig(unit: TemperatureUnit)
    suspend fun saveWindSpeedUnitConfig(unit: WindSpeedUnit)
}
