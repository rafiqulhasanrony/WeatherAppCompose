package com.example.baseapp.core.navigation

import androidx.annotation.VisibleForTesting
import androidx.navigation3.runtime.NavKey

class NavigatorImpl(
    private val state: NavigationState,
) : Navigator {

    override fun navigateTo(key: NavKey) {
        state.backStack.add(key)
    }

    override fun pop() {
        state.backStack.removeLastOrNull()
    }

    override fun replace(key: NavKey) {
        state.backStack.removeLastOrNull()
        state.backStack.add(key)
    }

    override fun resetToRoot() {
        val root = state.startKey
        state.backStack.clear()
        state.backStack.add(root)
    }

    @get:VisibleForTesting
    val backStack = state.backStack
}
