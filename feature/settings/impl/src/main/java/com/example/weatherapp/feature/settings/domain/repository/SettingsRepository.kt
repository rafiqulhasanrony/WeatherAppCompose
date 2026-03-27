package com.example.weatherapp.feature.settings.domain.repository

import com.example.weatherApp.feature.settings.publicApi.model.TemperatureUnit
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.WindSpeedUnit
import com.example.weatherapp.feature.settings.domain.model.TemperatureUnitConfig
import com.example.weatherapp.feature.settings.domain.model.ThemeConfig
import com.example.weatherapp.feature.settings.domain.model.WindSpeedUnitConfig
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun isDynamicThemeEnabled(): Flow<Boolean>
    fun getAvailableThemeConfig(): Flow<List<ThemeConfig>>
    fun getTemperatureUnitConfig(): Flow<List<TemperatureUnitConfig>>
    fun getWindSpeedUnitConfig(): Flow<List<WindSpeedUnitConfig>>

    suspend fun updateDynamicTheme(isEnabled: Boolean)
    suspend fun saveThemeConfig(themeType: ThemeType)
    suspend fun saveTemperatureUnitConfig(unit: TemperatureUnit)
    suspend fun saveWindSpeedUnitConfig(unit: WindSpeedUnit)
}
