package com.example.weatherapp.feature.settings.presentation.uimodel

import androidx.compose.runtime.Immutable
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherapp.core.designsystem.component.ListTitleProperties
import com.example.weatherapp.core.designsystem.component.SegmentedItem
import com.example.weatherapp.core.designsystem.foundation.textformat.TextData

@Immutable
data class ThemeConfigUiModel(
    val selectedTheme: ListTitleProperties = ListTitleProperties(
        title = TextData.empty(),
        subtitle = TextData.empty(),
    ),
    val segmentedItems: List<SegmentedItem<ThemeType>> = emptyList(),
)
