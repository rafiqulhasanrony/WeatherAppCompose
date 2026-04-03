package com.example.settings.data

import com.example.weatherApp.feature.settings.publicApi.R
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.core.designsystem.component.ListTitleProperties
import com.example.weatherapp.core.designsystem.component.SegmentedItem
import com.example.weatherapp.core.designsystem.foundation.textformat.TextData
import com.example.weatherapp.feature.settings.presentation.uimodel.SettingsUiModel
import com.example.weatherapp.feature.settings.presentation.uimodel.ThemeConfigUiModel
import com.example.weatherapp.feature.settings.presentation.uimodel.UnitOfMeasurementConfigUiModel

object SettingsTestData {

    fun getThemeConfigUiModel(selectedTheme: ThemeType) = ThemeConfigUiModel(
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

    fun getMeasurementConfigUiModel(selectedUnit: UnitOfMeasurement) = UnitOfMeasurementConfigUiModel(
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

    fun getSettingsUiModel(isDynamicThemeEnabled: Boolean, selectedTheme: ThemeType, selectedMeasurementUnit: UnitOfMeasurement) = SettingsUiModel(
        isDynamicThemeEnabled = isDynamicThemeEnabled,
        themeConfigUiModel = getThemeConfigUiModel(selectedTheme),
        unitOfMeasurementConfigUiModel = getMeasurementConfigUiModel(selectedMeasurementUnit),
    )
}
