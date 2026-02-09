package com.example.weatherapp.feature.weatherinfo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.core.designsystem.icons.AppIcons
import com.example.weatherapp.core.designsystem.theme.AppTheme
import com.example.weatherapp.core.ui.AppStandardToolbar

@Composable
internal fun WeatherInfoRoute(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    WeatherInfoScreen(
        onSearchClick = onSearchClick,
        onSettingsClick = onSettingsClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WeatherInfoScreen(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            Toolbar(
                onSearchClick = onSearchClick,
                onSettingsClick = onSettingsClick,
            )
        },
    ) { innerPadding ->
        Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Toolbar(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    AppStandardToolbar(
        title = "Weather Info",
        actions = {
            IconButton(
                onClick = {
                    onSearchClick.invoke()
                },
            ) {
                Icon(
                    imageVector = AppIcons.search,
                    contentDescription = "Location Search",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
            IconButton(
                onClick = {
                    onSettingsClick.invoke()
                },
            ) {
                Icon(
                    imageVector = AppIcons.settings,
                    contentDescription = "settings",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun WeatherInfoScreenPreview() {
    AppTheme {
        WeatherInfoScreen({}, {})
    }
}
