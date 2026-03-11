package com.example.weatherapp.feature.settings.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.baseapp.core.navigation.Navigator
import com.example.weatherApp.feature.settings.publicApi.navigation.SettingsNavKey
import com.example.weatherapp.feature.settings.SettingsScreen

fun EntryProviderScope<NavKey>.settingsEntry(navigator: Navigator) {
    entry<SettingsNavKey> {
        SettingsScreen(
            onBackPress = {
                navigator.goBack()
            },
        )
    }
}
