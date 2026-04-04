package com.example.settings.fake

import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.feature.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeSettingsRepository : SettingsRepository {

    private val dynamicTheme = MutableStateFlow(false)
    private val theme = MutableStateFlow(ThemeType.System)
    private val unit = MutableStateFlow(UnitOfMeasurement.Metric)

    override fun isDynamicThemeEnabled(): Flow<Boolean> = dynamicTheme
    override fun getSelectedTheme(): Flow<ThemeType> = theme
    override fun getSelectedMeasurementUnit(): Flow<UnitOfMeasurement> = unit

    override suspend fun updateDynamicTheme(isEnabled: Boolean) {
        dynamicTheme.value = isEnabled
    }

    override suspend fun saveThemeType(themeType: ThemeType) {
        theme.value = themeType
    }

    override suspend fun saveMeasurementUnitType(unitOfMeasurement: UnitOfMeasurement) {
        unit.value = unitOfMeasurement
    }

    // Test helpers
    fun setDynamicTheme(enabled: Boolean) { dynamicTheme.value = enabled }
    fun setTheme(themeType: ThemeType) { theme.value = themeType }
    fun setUnit(unit: UnitOfMeasurement) { this.unit.value = unit }
}
