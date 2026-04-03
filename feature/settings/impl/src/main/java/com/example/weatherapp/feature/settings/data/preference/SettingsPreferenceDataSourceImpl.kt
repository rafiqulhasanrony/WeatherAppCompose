package com.example.weatherapp.feature.settings.data.preference

import com.example.baseapp.core.datastore.PreferenceDataStoreAPI
import com.example.weatherApp.feature.settings.publicApi.datastore.SettingsDataStoreKeys
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.feature.settings.domain.preference.SettingsPreferenceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsPreferenceDataSourceImpl @Inject constructor(
    private val preferenceDataStoreAPI: PreferenceDataStoreAPI,
) : SettingsPreferenceDataSource {

    override fun isDynamicThemeEnabled(): Flow<Boolean> =
        preferenceDataStoreAPI.getPreference(
            key = SettingsDataStoreKeys.DYNAMIC_THEME_PREF_KEY,
            defaultValue = false,
        )

    override fun getSelectedTheme(): Flow<ThemeType> =
        preferenceDataStoreAPI.getPreference(
            key = SettingsDataStoreKeys.THEME_PREF_KEY,
            defaultValue = ThemeType.System.name,
        ).map { ThemeType.fromName(it) }

    override fun getSelectedMeasurementUnit(): Flow<UnitOfMeasurement> =
        preferenceDataStoreAPI.getPreference(
            key = SettingsDataStoreKeys.MEASUREMENT_UNIT_PREF_KEY,
            defaultValue = UnitOfMeasurement.Metric.name,
        ).map { UnitOfMeasurement.fromName(it) }

    override suspend fun saveDynamicTheme(isEnabled: Boolean) {
        preferenceDataStoreAPI.putPreference(
            key = SettingsDataStoreKeys.DYNAMIC_THEME_PREF_KEY,
            value = isEnabled,
        )
    }

    override suspend fun saveTheme(themeType: ThemeType) {
        preferenceDataStoreAPI.putPreference(
            key = SettingsDataStoreKeys.THEME_PREF_KEY,
            value = themeType.name,
        )
    }

    override suspend fun saveMeasurementUnit(unitOfMeasurement: UnitOfMeasurement) {
        preferenceDataStoreAPI.putPreference(
            key = SettingsDataStoreKeys.MEASUREMENT_UNIT_PREF_KEY,
            value = unitOfMeasurement.name,
        )
    }
}
