package com.example.settings.data.repository

import app.cash.turbine.test
import com.example.baseapp.core.testing.shouldEqual
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.feature.settings.data.repository.SettingsRepositoryImpl
import com.example.weatherapp.feature.settings.domain.preference.SettingsPreferenceDataSource
import com.example.weatherapp.feature.settings.domain.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

class SettingsRepositoryTest {
    val mockSettingsDataSource: SettingsPreferenceDataSource = mockk()
    private lateinit var settingsRepository: SettingsRepository

    @BeforeEach
    fun setup() {
        settingsRepository = SettingsRepositoryImpl(mockSettingsDataSource)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `GIVEN dynamic theme state THEN return correct value`(isEnabled: Boolean) = runTest {
        // arrange
        every { mockSettingsDataSource.isDynamicThemeEnabled() } returns flowOf(isEnabled)

        // act and verify
        settingsRepository.isDynamicThemeEnabled().test {
            assertEquals(awaitItem(), isEnabled)
            awaitComplete()
        }
    }

    @ParameterizedTest
    @EnumSource(ThemeType::class)
    fun `GIVEN selected theme type THEN return correct theme type`(themeType: ThemeType) = runTest {
        // Arrange
        every { mockSettingsDataSource.getSelectedTheme() } returns flowOf(themeType)

        // act and verify
        settingsRepository.getSelectedTheme().test {
            assertEquals(awaitItem(), themeType)
            awaitComplete()
        }
    }

    @ParameterizedTest
    @EnumSource(UnitOfMeasurement::class)
    fun `GIVEN selected measurement type THEN return correct measurement type`(unitOfMeasurement: UnitOfMeasurement) = runTest {
        // arrange
        every { mockSettingsDataSource.getSelectedMeasurementUnit() } returns flowOf(unitOfMeasurement)

        // act and verify
        settingsRepository.getSelectedMeasurementUnit().test {
            assertEquals(awaitItem(), unitOfMeasurement)
            awaitComplete()
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `WHEN save dynamic theme THEN save correct value`(isEnabled: Boolean) = runTest {
        // arrange
        val slot = slot<Boolean>()
        coEvery { mockSettingsDataSource.saveDynamicTheme(any()) } just runs

        // act
        settingsRepository.updateDynamicTheme(isEnabled)

        // verify
        coVerify(exactly = 1) { mockSettingsDataSource.saveDynamicTheme(capture(slot)) }
        confirmVerified(mockSettingsDataSource)
        slot.captured shouldEqual isEnabled
    }

    @ParameterizedTest
    @EnumSource(ThemeType::class)
    fun `WHEN save theme type THEN save correct value`(themeType: ThemeType) = runTest {
        // arrange
        val slot = slot<ThemeType>()
        coEvery { mockSettingsDataSource.saveTheme(any()) } just runs

        // act
        settingsRepository.saveThemeType(themeType)

        // verify
        coVerify(exactly = 1) { mockSettingsDataSource.saveTheme(capture(slot)) }
        confirmVerified(mockSettingsDataSource)
        slot.captured shouldEqual themeType
    }

    @ParameterizedTest
    @EnumSource(UnitOfMeasurement::class)
    fun `WHEN save measurement type THEN save correct value`(unitOfMeasurement: UnitOfMeasurement) = runTest {
        // Arrange
        val slot = slot<UnitOfMeasurement>()
        coEvery { mockSettingsDataSource.saveMeasurementUnit(any()) } just runs

        // Act
        settingsRepository.saveMeasurementUnitType(unitOfMeasurement)

        // verify
        coVerify(exactly = 1) { mockSettingsDataSource.saveMeasurementUnit(capture(slot)) }
        confirmVerified(mockSettingsDataSource)
        slot.captured shouldEqual unitOfMeasurement
    }
}
