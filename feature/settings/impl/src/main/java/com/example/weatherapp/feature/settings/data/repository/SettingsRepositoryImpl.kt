package com.example.weatherapp.feature.settings.data.repository

import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.feature.settings.domain.preference.SettingsPreferenceDataSource
import com.example.weatherapp.feature.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataSource: SettingsPreferenceDataSource,
) : SettingsRepository {

    override fun isDynamicThemeEnabled(): Flow<Boolean> =
        dataSource.isDynamicThemeEnabled()

    override fun getSelectedTheme(): Flow<ThemeType> = dataSource.getSelectedTheme()

    override fun getSelectedMeasurementUnit(): Flow<UnitOfMeasurement> = dataSource.getSelectedMeasurementUnit()

    override suspend fun updateDynamicTheme(isEnabled: Boolean) =
        dataSource.saveDynamicTheme(isEnabled)

    override suspend fun saveThemeType(themeType: ThemeType) =
        dataSource.saveTheme(themeType)

    override suspend fun saveMeasurementUnitType(unit: UnitOfMeasurement) =
        dataSource.saveMeasurementUnit(unit)
}
