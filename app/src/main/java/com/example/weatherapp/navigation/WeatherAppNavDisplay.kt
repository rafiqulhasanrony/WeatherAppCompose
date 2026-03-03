package com.example.weatherapp.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.weatherapp.feature.settings.navigation.settingsEntry
import com.example.weatherapp.feature.weatherinfo.navigation.weatherInfoEntry
import com.example.weatherapp.weatherinfo.publicapi.navigation.WeatherInfoNavKey

private const val NAV_ANIMATION_DURATION = 300

@Composable
fun WeatherAppNavDisplay(modifier: Modifier = Modifier) {
    val navBackStack = rememberNavBackStack(WeatherInfoNavKey)

    NavDisplay(
        modifier = modifier,
        backStack = navBackStack,
        onBack = { navBackStack.removeLastOrNull() },
        entryProvider = entryProvider {
            weatherInfoEntry(navBackStack)
            settingsEntry(navBackStack)
        },
        transitionSpec = {
            // Slide in from right when navigating forward
            forwardTransition(NAV_ANIMATION_DURATION)
        },
        popTransitionSpec = {
            // Slide in from left when navigating back
            popTransition(NAV_ANIMATION_DURATION)
        },
        predictivePopTransitionSpec = {
            // Slide in from left when navigating back
            popTransition(NAV_ANIMATION_DURATION)
        },
    )
}

private fun forwardTransition(duration: Int) =
    slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(duration),
    ) togetherWith slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(duration),
    )

private fun popTransition(duration: Int) =
    slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(duration),
    ) togetherWith slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(duration),
    )
