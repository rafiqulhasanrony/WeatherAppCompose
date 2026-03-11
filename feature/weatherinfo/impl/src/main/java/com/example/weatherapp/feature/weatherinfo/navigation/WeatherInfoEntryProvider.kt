package com.example.weatherapp.feature.weatherinfo.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.baseapp.core.navigation.Navigator
import com.example.weatherApp.feature.settings.publicApi.navigation.SettingsNavKey
import com.example.weatherapp.feature.weatherinfo.WeatherInfoScreen
import com.example.weatherapp.weatherinfo.publicapi.navigation.WeatherInfoNavKey

fun EntryProviderScope<NavKey>.weatherInfoEntry(navigator: Navigator) {
    entry<WeatherInfoNavKey> {
        WeatherInfoScreen(
            onSearchClick = { },
            onSettingsClick = { navigator.goTo(SettingsNavKey) },
        )
    }
}
