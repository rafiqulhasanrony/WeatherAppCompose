package com.example.weatherapp.feature.settings

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherApp.feature.settings.publicApi.R.string
import com.example.weatherapp.core.designsystem.component.AppList
import com.example.weatherapp.core.designsystem.component.AppSegmentedButton
import com.example.weatherapp.core.designsystem.component.ListTitleProperties
import com.example.weatherapp.core.designsystem.component.SegmentedItem
import com.example.weatherapp.core.designsystem.component.TitleWithSwitch
import com.example.weatherapp.core.designsystem.component.TitleWithTag
import com.example.weatherapp.core.designsystem.foundation.AppText
import com.example.weatherapp.core.designsystem.foundation.textformat.TextData
import com.example.weatherapp.core.designsystem.theme.Spacing
import com.example.weatherapp.core.ui.AppStandardToolbar

@Composable
internal fun SettingsRoute(onBackPress: () -> Unit) {
    SettingsScreen(onBackPress)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    onBackPress: () -> Unit,
) {
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
        SettingsContentScreenView(modifier = Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
fun SettingsContentScreenView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,

        ) {
        AppText(
            textData = TextData.of(string.feature_settings_public_api_title_display),
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = Spacing.md),
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacing.md),
        )
        AppList.TitleWithSwitch(
            listTitleProperties = ListTitleProperties(
                TextData.of(string.feature_settings_public_api_dynamic_theme),
            ),
            checked = false,
            onCheckedChange = { isChecked ->
            },
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacing.xs),
        )

        AppList.TitleWithTag(
            listTitleProperties = ListTitleProperties(
                TextData.of(string.feature_settings_public_api_current_theme),
                TextData.of(string.feature_settings_public_api_theme_dark),
            ),
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacing.md),
        )

        SegmentedButton(
            listOf(
                SegmentedItem(TextData.of("Dark")),
                SegmentedItem(TextData.of("Light"), true),
                SegmentedItem(TextData.of("System")),
            ),
        )

        //unit of measurement
        AppText(
            textData = TextData.of(string.feature_settings_public_api_title_unit_of_measurement),
            textStyle = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(Spacing.md),
        )

        AppList.TitleWithTag(
            listTitleProperties = ListTitleProperties(
                TextData.of(string.feature_settings_public_api_temperature),
            ),
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacing.md),
        )

        SegmentedButton(
            listOf(
                SegmentedItem(TextData.of("Celsius")),
                SegmentedItem(TextData.of("Fahrenheit"), true),
            ),
        )

        AppList.TitleWithTag(
            listTitleProperties = ListTitleProperties(
                TextData.of(string.feature_settings_public_api_wind_speed),
            ),
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacing.md),
        )

        SegmentedButton(
            listOf(
                SegmentedItem(TextData.of("km/h")),
                SegmentedItem(TextData.of("mph"),),
                SegmentedItem(TextData.of("m/s"), true),
            ),
        )
    }
}

@Composable
private fun SegmentedButton(segmentedItems: List<SegmentedItem>) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,

        ) {
        AppSegmentedButton(
            items = segmentedItems,
            onSelectionChange = { _, _ -> },
        )
    }
}
