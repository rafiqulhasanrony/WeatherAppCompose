package com.example.settings.data.presentaion.mapper

import com.example.baseapp.core.testing.shouldEqual
import com.example.weatherApp.feature.settings.publicApi.R
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.core.designsystem.component.ListTitleProperties
import com.example.weatherapp.core.designsystem.component.SegmentedItem
import com.example.weatherapp.core.designsystem.foundation.textformat.TextData
import com.example.weatherapp.feature.settings.presentation.mapper.SettingsUiMapper
import com.example.weatherapp.feature.settings.presentation.uimodel.ThemeConfigUiModel
import com.example.weatherapp.feature.settings.presentation.uimodel.UnitOfMeasurementConfigUiModel
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class SettingsUiMapperTest {

    @ParameterizedTest
    @EnumSource(ThemeType::class)
    fun `WHEN selected theme is provided THEN return correct ThemeConfigUiModel`(selectedTheme: ThemeType) {
        //Act
        val themeConfigUiModel = SettingsUiMapper.toThemeConfigUiModel(selectedTheme)


        //Verify
        val expectedThemeConfigUiModel = getThemeConfigUiModel(selectedTheme)

        themeConfigUiModel shouldEqual expectedThemeConfigUiModel
    }

    @ParameterizedTest
    @EnumSource(UnitOfMeasurement::class)
    fun `WHEN selected unit of measurement is provided THEN return correct UnitOfMeasurementConfigUiModel`(selectedUnit: UnitOfMeasurement) {
        //Act
        val unitOfMeasurementConfigUiModel = SettingsUiMapper.toUnitConfigUiModel(selectedUnit)

        //Verify
        val expectedUnitOfMeasurementConfigUiModel = getMeasurementConfigUiModel(selectedUnit)

        unitOfMeasurementConfigUiModel shouldEqual expectedUnitOfMeasurementConfigUiModel

    }

    private fun getThemeConfigUiModel(selectedTheme: ThemeType) = ThemeConfigUiModel(
        selectedTheme = ListTitleProperties(
            title = TextData.of(R.string.feature_settings_public_api_current_theme),
            subtitle = TextData.of(selectedTheme.stringRes),
        ),
        segmentedItems = listOf(
            SegmentedItem(
                textData = TextData.of(ThemeType.Dark.stringRes),
                isSelected = selectedTheme == ThemeType.Dark,
                type = ThemeType.Dark,
            ),
            SegmentedItem(
                textData = TextData.of(ThemeType.Light.stringRes),
                isSelected = selectedTheme == ThemeType.Light,
                type = ThemeType.Light,
            ),
            SegmentedItem(
                textData = TextData.of(ThemeType.System.stringRes),
                isSelected = selectedTheme == ThemeType.System,
                type = ThemeType.System,
            ),
        ),
    )

    private fun getMeasurementConfigUiModel(selectedUnit: UnitOfMeasurement) = UnitOfMeasurementConfigUiModel(
        selectedMeasurementUnit = ListTitleProperties(
            title = TextData.of(R.string.feature_settings_public_api_title_unit_of_measurement),
            subtitle = TextData.of(selectedUnit.stringRes),
        ),
        segmentedItems = listOf(
            SegmentedItem(
                textData = TextData.of(UnitOfMeasurement.Metric.stringRes),
                isSelected = selectedUnit == UnitOfMeasurement.Metric,
                type = UnitOfMeasurement.Metric,
            ),
            SegmentedItem(
                textData = TextData.of(UnitOfMeasurement.Imperial.stringRes),
                isSelected = selectedUnit == UnitOfMeasurement.Imperial,
                type = UnitOfMeasurement.Imperial,
            ),
        ),
    )
}


