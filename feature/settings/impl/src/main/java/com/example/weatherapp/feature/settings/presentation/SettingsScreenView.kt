package com.example.weatherapp.feature.settings.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherApp.feature.settings.publicApi.R.string
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.core.designsystem.component.AppList
import com.example.weatherapp.core.designsystem.component.AppSegmentedButton
import com.example.weatherapp.core.designsystem.component.ListTitleProperties
import com.example.weatherapp.core.designsystem.component.TitleWithSwitch
import com.example.weatherapp.core.designsystem.component.TitleWithTag
import com.example.weatherapp.core.designsystem.foundation.textformat.TextData
import com.example.weatherapp.core.designsystem.theme.Spacing
import com.example.weatherapp.core.ui.AppStandardToolbar
import com.example.weatherapp.feature.settings.presentation.uimodel.ThemeConfigUiModel
import com.example.weatherapp.feature.settings.presentation.uimodel.UnitOfMeasurementConfigUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    onBackPress: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val settingsViewModel by viewModel.settingsUiModel.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            AppStandardToolbar(
                title = TextData.of(string.feature_settings_public_api_toolbar_title),
                onBackClick = onBackPress,
            )
        },

    ) { innerPadding ->

        SettingsContentScreenView(
            modifier = Modifier.padding(innerPadding),
            isDynamicThemeEnabled = settingsViewModel.isDynamicThemeEnabled,
            themeConfigUiModel = settingsViewModel.themeConfigUiModel,
            unitOfMeasurementConfigUiModel = settingsViewModel.unitOfMeasurementConfigUiModel,
            onDynamicThemeChange = viewModel::updateDynamicTheme,
            onThemeSelectionChange = viewModel::updateTheme,
            onUnitMeasurementChange = viewModel::updateUnitOfMeasurement,
        )
    }
}

@Composable
fun SettingsContentScreenView(
    modifier: Modifier = Modifier,
    isDynamicThemeEnabled: Boolean,
    themeConfigUiModel: ThemeConfigUiModel,
    unitOfMeasurementConfigUiModel: UnitOfMeasurementConfigUiModel,
    onDynamicThemeChange: (Boolean) -> Unit,
    onThemeSelectionChange: (ThemeType) -> Unit,
    onUnitMeasurementChange: (UnitOfMeasurement) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,

    ) {
        DynamicThemeContent(
            isDynamicEnabled = isDynamicThemeEnabled,
            onDynamicThemeChange = onDynamicThemeChange,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacing.xs),
        )

        ThemeConfigContent(
            uiModel = themeConfigUiModel,
            onThemeSelectionChange = onThemeSelectionChange,
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacing.md),
        )

        UnitOfMeasurementContent(
            uiModel = unitOfMeasurementConfigUiModel,
            onUnitMeasurementChange = onUnitMeasurementChange,
        )
    }
}

@Composable
private fun DynamicThemeContent(isDynamicEnabled: Boolean, onDynamicThemeChange: (Boolean) -> Unit) {
    AppList.TitleWithSwitch(
        listTitleProperties = ListTitleProperties(
            TextData.of(string.feature_settings_public_api_dynamic_theme),
        ),
        checked = isDynamicEnabled,
        onCheckedChange = onDynamicThemeChange,
    )
}

@Composable
private fun ThemeConfigContent(
    uiModel: ThemeConfigUiModel,
    onThemeSelectionChange: (ThemeType) -> Unit,
) {
    SettingSelectedItem(listTitleProperties = uiModel.selectedTheme)

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(Spacing.md),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,

    ) {
        AppSegmentedButton(
            items = uiModel.segmentedItems,
            onSelectionChange = { _, themeType ->
                onThemeSelectionChange(themeType)
            },
        )
    }
}

@Composable
private fun UnitOfMeasurementContent(
    uiModel: UnitOfMeasurementConfigUiModel,
    onUnitMeasurementChange: (UnitOfMeasurement) -> Unit,
) {
    SettingSelectedItem(listTitleProperties = uiModel.selectedMeasurementUnit)

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(Spacing.md),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,

    ) {
        AppSegmentedButton(
            items = uiModel.segmentedItems,
            onSelectionChange = { _, themeType ->
                onUnitMeasurementChange(themeType)
            },
        )
    }
}

@Composable
private fun SettingSelectedItem(listTitleProperties: ListTitleProperties) {
    AppList.TitleWithTag(listTitleProperties = listTitleProperties)
}
