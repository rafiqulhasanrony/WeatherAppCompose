package com.example.weatherApp.feature.settings.publicApi.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Created By Rafiqul Hasan
 */
object SettingsDataStoreKeys {
    val DYNAMIC_THEME_PREF_KEY = booleanPreferencesKey(Keys.DYNAMIC_THEME)
    val THEME_PREF_KEY = stringPreferencesKey(Keys.THEME_MODE)
    val TEMPERATURE_UNIT_MEASUREMENT_PREF_KEY = stringPreferencesKey(Keys.TEMPERATURE_UNIT_MEASUREMENT)
    val WIND_SPEED_UNIT_MEASUREMENT_PREF_KEY = stringPreferencesKey(Keys.WIND_SPEED_UNIT_MEASUREMENT)
}
