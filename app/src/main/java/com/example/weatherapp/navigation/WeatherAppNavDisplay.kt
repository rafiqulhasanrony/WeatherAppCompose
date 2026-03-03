package com.example.weatherapp.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.weatherapp.feature.settings.navigation.settingsEntry
import com.example.weatherapp.feature.weatherinfo.navigation.weatherInfoEntry
import com.example.weatherapp.weatherinfo.publicapi.navigation.WeatherInfoNavKey

@Composable
fun WeatherAppNavDisplay  (modifier: Modifier = Modifier) {
    val navBackStack = rememberNavBackStack(WeatherInfoNavKey)

    NavDisplay(
        backStack = navBackStack,
        onBack = { navBackStack.removeLastOrNull() },
        entryProvider = entryProvider {
            weatherInfoEntry(navBackStack)
            settingsEntry(navBackStack)
        }
    )
}
