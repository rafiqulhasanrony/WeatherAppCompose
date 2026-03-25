package com.example.weatherapp.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.baseapp.core.navigation.EntryProviderInstaller
import com.example.baseapp.core.navigation.LocalNavigator
import com.example.baseapp.core.navigation.NavigatorImpl
import com.example.baseapp.core.navigation.rememberNavigationState
import com.example.weatherapp.weatherinfo.publicapi.navigation.WeatherInfoNavKey

private const val NAV_ANIMATION_DURATION = 300

@Composable
fun WeatherAppNavDisplay(
    modifier: Modifier = Modifier,
    entryInstallers: Set<EntryProviderInstaller>,
    startKey: NavKey = WeatherInfoNavKey,
) {
    val navigationState = rememberNavigationState(startKey)

    val navigator = remember(navigationState) { NavigatorImpl(navigationState) }

    val entryProvider = remember(navigator) {
        entryProvider {
            entryInstallers.forEach { installer ->
                installer(navigator)
            }
        }
    }

    CompositionLocalProvider(LocalNavigator provides navigator) {
        NavDisplay(
            modifier = modifier,
            backStack = navigationState.backStack,

            onBack = {
                navigator.pop()
            },

            entryProvider = entryProvider,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
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
