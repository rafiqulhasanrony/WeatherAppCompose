package com.example.weatherapp.feature.settings.data.repository

import com.example.weatherApp.feature.settings.publicApi.model.TemperatureUnit
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.WindSpeedUnit
import com.example.weatherapp.feature.settings.domain.model.TemperatureUnitConfig
import com.example.weatherapp.feature.settings.domain.model.ThemeConfig
import com.example.weatherapp.feature.settings.domain.model.WindSpeedUnitConfig
import com.example.weatherapp.feature.settings.domain.preference.SettingsPreferenceDataSource
import com.example.weatherapp.feature.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataSource: SettingsPreferenceDataSource,
) : SettingsRepository {

    override suspend fun isDynamicThemeEnabled(): Flow<Boolean> =
        dataSource.isDynamicThemeEnabled()

    override suspend fun getAvailableThemeConfig(): Flow<List<ThemeConfig>> =
        dataSource.getTheme().map { selectedType ->
            ThemeType.entries.map { type ->
                ThemeConfig(
                    themeTypeStringRes = type.stringRes,
                    themeType = type,
                    isSelected = type == selectedType,
                )
            }
        }

    override suspend fun getTemperatureUnitConfig(): Flow<List<TemperatureUnitConfig>> =
        dataSource.getTemperatureUnit().map { selectedUnit ->
            TemperatureUnit.entries.map { unit ->
                TemperatureUnitConfig(
                    temperatureUnitStringRes = unit.stringRes,
                    temperatureUnit = unit,
                    isSelected = unit == selectedUnit,
                )
            }
        }

    override suspend fun getWindSpeedUnitConfig(): Flow<List<WindSpeedUnitConfig>> =
        dataSource.getWindSpeedUnit().map { selectedUnit ->
            WindSpeedUnit.entries.map { unit ->
                WindSpeedUnitConfig(
                    windSpeedUnitStringRes = unit.stringRes,
                    windSpeedUnit = unit,
                    isSelected = unit == selectedUnit,
                )
            }
        }

    override suspend fun updateDynamicTheme(isEnabled: Boolean) =
        dataSource.saveDynamicTheme(isEnabled)

    override suspend fun saveThemeConfig(themeType: ThemeType) =
        dataSource.saveTheme(themeType)

    override suspend fun saveTemperatureUnitConfig(unit: TemperatureUnit) =
        dataSource.saveTemperatureUnit(unit)

    override suspend fun saveWindSpeedUnitConfig(unit: WindSpeedUnit) =
        dataSource.saveWindSpeedUnit(unit)
}
