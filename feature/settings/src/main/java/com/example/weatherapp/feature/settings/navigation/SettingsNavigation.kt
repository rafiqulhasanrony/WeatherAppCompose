package com.example.weatherapp.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherapp.feature.settings.SettingsRoute
import kotlinx.serialization.Serializable

@Serializable
data object SettingsRoute

fun NavController.navigateToSetting(){
    navigate(SettingsRoute)
}

fun NavGraphBuilder.settingsScreen(onBackPress: () -> Unit) {
    composable<SettingsRoute> {
        SettingsRoute(onBackPress)
    }

}