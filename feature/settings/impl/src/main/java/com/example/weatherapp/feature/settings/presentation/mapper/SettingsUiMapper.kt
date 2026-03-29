package com.example.weatherapp.feature.settings.presentation.mapper

import com.example.weatherApp.feature.settings.publicApi.R
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.core.designsystem.component.ListTitleProperties
import com.example.weatherapp.core.designsystem.component.SegmentedItem
import com.example.weatherapp.core.designsystem.foundation.textformat.TextData
import com.example.weatherapp.feature.settings.presentation.uimodel.ThemeConfigUiModel
import com.example.weatherapp.feature.settings.presentation.uimodel.UnitOfMeasurementConfigUiModel

object SettingsUiMapper {

    fun toThemeConfigUiModel(selected: ThemeType): ThemeConfigUiModel =
        ThemeConfigUiModel(
            selectedTheme = ListTitleProperties(
                title = TextData.of(R.string.feature_settings_public_api_current_theme),
                subtitle = TextData.of(selected.stringRes),
            ),
            segmentedItems = ThemeType.entries.map { it.toSegmentedItem(selected) },
        )

    fun toUnitConfigUiModel(selected: UnitOfMeasurement): UnitOfMeasurementConfigUiModel =
        UnitOfMeasurementConfigUiModel(
            selectedMeasurementUnit = ListTitleProperties(
                title = TextData.of(R.string.feature_settings_public_api_title_unit_of_measurement),
                subtitle = TextData.of(selected.stringRes),
            ),
            segmentedItems = UnitOfMeasurement.entries.map { it.toSegmentedItem(selected) },
        )

    private fun ThemeType.toSegmentedItem(selected: ThemeType): SegmentedItem<ThemeType> =
        SegmentedItem(
            textData = TextData.of(stringRes),
            isSelected = this == selected,
            type = this,
        )

    private fun UnitOfMeasurement.toSegmentedItem(selected: UnitOfMeasurement): SegmentedItem<UnitOfMeasurement> =
        SegmentedItem(
            textData = TextData.of(stringRes),
            isSelected = this == selected,
            type = this,
        )
}
