package com.example.settings

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import com.example.settings.fake.FakeSettingsRepository
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.core.designsystem.component.AppBackground
import com.example.weatherapp.core.designsystem.component.SEGMENTED_BUTTON_TEST_TAG
import com.example.weatherapp.core.designsystem.theme.AppTheme
import com.example.weatherapp.feature.settings.domain.repository.SettingsRepository
import com.example.weatherapp.feature.settings.presentation.SettingsScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class SettingsScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<HiltTestActivity>()

    @Inject
    lateinit var settingsRepository: SettingsRepository

    private val fakeRepository get() = settingsRepository as FakeSettingsRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun givenMetricUnit_whenScreenLaunches_thenMetricSegmentIsSelected() {
        fakeRepository.setUnit(UnitOfMeasurement.Metric)

        launchSettingsScreen()
        composeRule.onAllNodesWithText("Metric").filter(hasTestTag(SEGMENTED_BUTTON_TEST_TAG)).onFirst().assertIsSelected()
    }

    private fun launchSettingsScreen() {
        composeRule.setContent {
            AppTheme {
                AppBackground {
                    SettingsScreen(onBackPress = {})
                }
            }
        }
    }
}
