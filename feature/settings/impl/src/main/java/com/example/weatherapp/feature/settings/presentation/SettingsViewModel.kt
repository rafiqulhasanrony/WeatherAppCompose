package com.example.weatherapp.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.feature.settings.domain.repository.SettingsRepository
import com.example.weatherapp.feature.settings.presentation.mapper.SettingsUiMapper
import com.example.weatherapp.feature.settings.presentation.mapper.SettingsUiMapper.toThemeConfigUiModel
import com.example.weatherapp.feature.settings.presentation.mapper.SettingsUiMapper.toUnitConfigUiModel
import com.example.weatherapp.feature.settings.presentation.uimodel.ThemeConfigUiModel
import com.example.weatherapp.feature.settings.presentation.uimodel.UnitOfMeasurementConfigUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val settingsRepository: SettingsRepository) : ViewModel() {

    val isDynamicThemeEnabled = settingsRepository.isDynamicThemeEnabled()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    val themeConfigUiModel = settingsRepository
        .getSelectedTheme()
        .map(SettingsUiMapper::toThemeConfigUiModel)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ThemeConfigUiModel(),
        )

    val unitOfMeasurementConfigUiModel = settingsRepository
        .getSelectedMeasurementUnit()
        .map(SettingsUiMapper::toUnitConfigUiModel)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UnitOfMeasurementConfigUiModel(),
        )

    fun updateDynamicTheme(isEnabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateDynamicTheme(isEnabled)
        }
    }

    fun updateTheme(themeType: ThemeType) {
        viewModelScope.launch {
            settingsRepository.saveThemeType(themeType)
        }
    }

    fun updateUnitOfMeasurement(unitOfMeasurement: UnitOfMeasurement) {
        viewModelScope.launch {
            settingsRepository.saveMeasurementUnitType(unitOfMeasurement)
        }
    }
}
