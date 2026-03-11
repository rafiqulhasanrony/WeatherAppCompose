package com.example.baseapp.core.navigation

import androidx.compose.runtime.Stable
import androidx.navigation3.runtime.NavKey

@Stable
class Navigator(private val navigationState: NavigationState) {

    fun goBack() {
        navigationState.backStack.removeLastOrNull()
    }

    fun goTo(navKey: NavKey) {
        navigationState.backStack.apply {
            // remove if it already in the stack, then added to top to avoid duplicate screen in backstack.
            remove(navKey)
            add(navKey)
        }
    }
}
