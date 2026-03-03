package com.example.weatherapp.feature.settings.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.weatherApp.feature.settings.publicApi.navigation.SettingsNavKey
import com.example.weatherapp.feature.settings.SettingsScreen

fun EntryProviderScope<NavKey>.settingsEntry(backStack: NavBackStack<NavKey>) {
    entry<SettingsNavKey> {
        SettingsScreen(
            onBackPress = {
                backStack.removeLastOrNull()
            },
        )
    }
}
