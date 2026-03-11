package com.example.baseapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack

@Composable
fun rememberNavigationState(
    startKey: NavKey,
): NavigationState {
    val navBackStack = rememberNavBackStack(startKey)

    return remember(startKey) {
        NavigationState(
            startKey = startKey,
            backStack = navBackStack,
        )
    }
}

class NavigationState(val startKey: NavKey, val backStack: NavBackStack<NavKey>)
