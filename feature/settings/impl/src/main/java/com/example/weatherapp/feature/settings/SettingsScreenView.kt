package com.example.weatherapp.feature.settings

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherApp.feature.settings.publicApi.R.string
import com.example.weatherapp.core.designsystem.component.AppList
import com.example.weatherapp.core.designsystem.component.ListTitleProperties
import com.example.weatherapp.core.designsystem.component.TitleWithSwitch
import com.example.weatherapp.core.designsystem.component.TitleWithTag
import com.example.weatherapp.core.designsystem.foundation.textformat.TextData
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
        modifier = modifier,

        ) {
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
                .height(2.dp),
        )

        AppList.TitleWithTag(
            listTitleProperties = ListTitleProperties(
                TextData.of(string.feature_settings_public_api_current_theme),
                TextData.of(string.feature_settings_public_api_theme_dark),
            ),
        )
    }
}
