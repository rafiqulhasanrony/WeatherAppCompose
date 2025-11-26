package com.example.weatherapp.feature.weatherinfo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherapp.feature.weatherinfo.WeatherInfoRoute
import kotlinx.serialization.Serializable


@Serializable
data object WeatherInfoRoute

fun NavGraphBuilder.weatherInfoScreen(onSearchClick: () -> Unit, onSettingClick: () -> Unit) {
    composable<WeatherInfoRoute> {
        WeatherInfoRoute(
            onSearchClick = onSearchClick,
            onSettingsClick = onSettingClick
        )
    }

}


/**
 * Lets say I have a card module, card moudle can have card management, order, setting etc screen
 * all of these nested navigation could be implemented under navigation<WeatherInfoBaseRoute> graph
 * other module do not have to know about  card management, order, setting, they could simply call NavigateToWeatherInfo
 */
//@Serializable data object WeatherInfoBaseRoute
//@Serializable data object WeatherInfoRoute
//fun NavGraphBuilder.NavigateToWeatherInfo(goToNextScreen: () -> Unit) {
//    navigation<WeatherInfoBaseRoute>(startDestination = WeatherInfoRoute) {
//        composable<WeatherInfoRoute> {
//            WeatherInfoScreen()
//        }
//
//    }
//}