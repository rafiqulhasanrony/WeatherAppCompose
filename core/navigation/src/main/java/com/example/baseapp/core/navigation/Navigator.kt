package com.example.baseapp.core.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

typealias EntryProviderInstaller = EntryProviderScope<NavKey>.(Navigator) -> Unit
interface Navigator {

    fun navigateTo(key: NavKey)

    fun pop()

    fun replace(key: NavKey)

    fun resetToRoot()
}
