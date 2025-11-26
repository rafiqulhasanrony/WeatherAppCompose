package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.feature.settings.navigation.navigateToSetting
import com.example.weatherapp.feature.settings.navigation.settingsScreen
import com.example.weatherapp.feature.weatherinfo.navigation.WeatherInfoRoute
import com.example.weatherapp.feature.weatherinfo.navigation.weatherInfoScreen

@Composable
fun WeatherAppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = WeatherInfoRoute,
        modifier = modifier
    ) {
        weatherInfoScreen(
            onSearchClick = navController::navigateToSetting,
            onSettingClick = navController::navigateToSetting
        )

        settingsScreen(
            onBackPress = navController::popBackStack
        )
    }

}