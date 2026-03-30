package com.example.weatherapp.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.feature.settings.domain.repository.SettingsRepository
import com.example.weatherapp.feature.settings.presentation.mapper.SettingsUiMapper
import com.example.weatherapp.feature.settings.presentation.uimodel.SettingsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val settingsRepository: SettingsRepository) : ViewModel() {

    val settingsUiModel = combine(
        settingsRepository.isDynamicThemeEnabled(),
        settingsRepository.getSelectedTheme(),
        settingsRepository.getSelectedMeasurementUnit(),
    ) { isDynamicThemeEnabled, selectedTheme, selectedMeasurementUnit ->
        SettingsUiModel(
            isDynamicThemeEnabled = isDynamicThemeEnabled,
            themeConfigUiModel = SettingsUiMapper.toThemeConfigUiModel(selectedTheme),
            unitOfMeasurementConfigUiModel = SettingsUiMapper.toUnitConfigUiModel(selectedMeasurementUnit),
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SettingsUiModel(),
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
