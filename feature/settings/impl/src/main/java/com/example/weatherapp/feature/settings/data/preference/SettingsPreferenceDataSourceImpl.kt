package com.example.weatherapp.feature.settings.data.preference

import com.example.baseapp.core.datastore.PreferenceDataStoreAPI
import com.example.weatherApp.feature.settings.publicApi.datastore.SettingsDataStoreKeys
import com.example.weatherApp.feature.settings.publicApi.model.TemperatureUnit
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.WindSpeedUnit
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

    override fun getTheme(): Flow<ThemeType> =
        preferenceDataStoreAPI.getPreference(
            key = SettingsDataStoreKeys.THEME_PREF_KEY,
            defaultValue = ThemeType.System.name,
        ).map { ThemeType.fromName(it) }

    override fun getTemperatureUnit(): Flow<TemperatureUnit> =
        preferenceDataStoreAPI.getPreference(
            key = SettingsDataStoreKeys.TEMPERATURE_UNIT_MEASUREMENT_PREF_KEY,
            defaultValue = TemperatureUnit.Celsius.name,
        ).map { TemperatureUnit.fromName(it) }

    override fun getWindSpeedUnit(): Flow<WindSpeedUnit> =
        preferenceDataStoreAPI.getPreference(
            key = SettingsDataStoreKeys.WIND_SPEED_UNIT_MEASUREMENT_PREF_KEY,
            defaultValue = WindSpeedUnit.KilometersPerHour.name,
        ).map { WindSpeedUnit.fromName(it) }

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

    override suspend fun saveTemperatureUnit(unit: TemperatureUnit) {
        preferenceDataStoreAPI.putPreference(
            key = SettingsDataStoreKeys.TEMPERATURE_UNIT_MEASUREMENT_PREF_KEY,
            value = unit.name,
        )
    }

    override suspend fun saveWindSpeedUnit(unit: WindSpeedUnit) {
        preferenceDataStoreAPI.putPreference(
            key = SettingsDataStoreKeys.WIND_SPEED_UNIT_MEASUREMENT_PREF_KEY,
            value = unit.name,
        )
    }
}
