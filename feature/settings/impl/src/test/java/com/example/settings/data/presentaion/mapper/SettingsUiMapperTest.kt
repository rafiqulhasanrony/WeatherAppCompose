package com.example.settings.data.presentaion.mapper

import com.example.baseapp.core.testing.shouldEqual
import com.example.settings.data.SettingsTestData.getMeasurementConfigUiModel
import com.example.settings.data.SettingsTestData.getThemeConfigUiModel
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.feature.settings.presentation.mapper.SettingsUiMapper
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class SettingsUiMapperTest {

    @ParameterizedTest
    @EnumSource(ThemeType::class)
    fun `WHEN selected theme is provided THEN return correct ThemeConfigUiModel`(selectedTheme: ThemeType) {
        // Act
        val themeConfigUiModel = SettingsUiMapper.toThemeConfigUiModel(selectedTheme)

        // Verify
        val expectedThemeConfigUiModel = getThemeConfigUiModel(selectedTheme)
        themeConfigUiModel shouldEqual expectedThemeConfigUiModel
    }

    @ParameterizedTest
    @EnumSource(UnitOfMeasurement::class)
    fun `WHEN selected unit of measurement is provided THEN return correct UnitOfMeasurementConfigUiModel`(selectedUnit: UnitOfMeasurement) {
        // Act
        val unitOfMeasurementConfigUiModel = SettingsUiMapper.toUnitConfigUiModel(selectedUnit)

        // Verify
        val expectedUnitOfMeasurementConfigUiModel = getMeasurementConfigUiModel(selectedUnit)
        unitOfMeasurementConfigUiModel shouldEqual expectedUnitOfMeasurementConfigUiModel
    }
}
