package com.example.weatherapp.feature.settings.presentation.uimodel

import androidx.compose.runtime.Immutable
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.core.designsystem.component.ListTitleProperties
import com.example.weatherapp.core.designsystem.component.SegmentedItem
import com.example.weatherapp.core.designsystem.foundation.textformat.TextData

@Immutable
data class UnitOfMeasurementConfigUiModel(
    val selectedMeasurementUnit: ListTitleProperties = ListTitleProperties(
        title = TextData.empty(),
        subtitle = TextData.empty(),
    ),
    val segmentedItems: List<SegmentedItem<UnitOfMeasurement>> = emptyList(),
)
