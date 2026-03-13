package com.example.baseapp.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.baseapp.core.testing.shouldEqual
import org.junit.Before
import org.junit.Test

private object TestKeyFirst : NavKey
private object TestKeySecond : NavKey

class NavigatorTest {
    private lateinit var navigator: NavigatorImpl
    private lateinit var navigationState: NavigationState

    @Before
    fun setup() {
        val startKey = TestKeyFirst
        navigationState = NavigationState(startKey, NavBackStack(startKey))
        navigator = NavigatorImpl(navigationState)
    }

    @Test
    fun `GIVEN first key THEN NavigationState contains first key`() {
        navigationState.startKey shouldEqual TestKeyFirst
    }

    @Test
    fun `GIVEN first key THEN backstack last is first key`() {
        navigator.backStack.last() shouldEqual TestKeyFirst
    }

    @Test
    fun `GIVEN second key THEN backstack last is second key`() {
        navigator.navigateTo(TestKeySecond)
        navigator.backStack.last() shouldEqual TestKeySecond
    }

    @Test
    fun `GIVEN two key in backstack WHEN pop THEN backstack last is first key`() {
        navigator.navigateTo(TestKeySecond)
        navigator.backStack.last() shouldEqual TestKeySecond

        navigator.pop()
        navigator.backStack.last() shouldEqual TestKeyFirst
        navigator.backStack.size shouldEqual 1
    }

    @Test
    fun `WHEN navigator replace THEN backstack last is replace`() {
        navigator.replace(TestKeySecond)
        navigator.backStack.last() shouldEqual TestKeySecond
        navigator.backStack.size shouldEqual 1
    }

    @Test
    fun `WHEN navigator rest to root THEN backstack last is first key`() {
        navigator.navigateTo(TestKeySecond)
        navigator.backStack.size shouldEqual 2

        navigator.resetToRoot()
        navigator.backStack.last() shouldEqual TestKeyFirst
        navigator.backStack.size shouldEqual 1
    }
}
