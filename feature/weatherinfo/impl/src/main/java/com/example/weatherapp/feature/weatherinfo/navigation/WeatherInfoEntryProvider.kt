package com.example.weatherapp.feature.weatherinfo.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.weatherApp.feature.settings.publicApi.navigation.SettingsNavKey
import com.example.weatherapp.feature.weatherinfo.WeatherInfoScreen
import com.example.weatherapp.weatherinfo.publicapi.navigation.WeatherInfoNavKey

fun EntryProviderScope<NavKey>.weatherInfoEntry(backStack: NavBackStack<NavKey>) {
    entry<WeatherInfoNavKey> {
        WeatherInfoScreen(
            onSearchClick = { },
            onSettingsClick = { backStack.add(SettingsNavKey) },
        )
    }
}
