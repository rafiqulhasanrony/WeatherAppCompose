package com.example.baseapp.core.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

typealias EntryProviderInstaller = EntryProviderScope<NavKey>.(Navigator) -> Unit

interface Navigator {

    fun navigateTo(key: NavKey)

    fun pop()

    fun replace(key: NavKey)

    fun resetToRoot()
}

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("No Navigator provided — wrap your UI in WeatherAppNavDisplay")
}
