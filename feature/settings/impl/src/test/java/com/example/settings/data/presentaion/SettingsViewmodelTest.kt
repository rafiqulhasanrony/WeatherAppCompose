package com.example.settings.data.presentaion

import app.cash.turbine.test
import com.example.baseapp.core.testing.MainDispatcherExtension
import com.example.baseapp.core.testing.shouldEqual
import com.example.settings.data.SettingsTestData
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.feature.settings.domain.repository.SettingsRepository
import com.example.weatherapp.feature.settings.presentation.SettingsViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(MainDispatcherExtension::class)
class SettingsViewmodelTest {

    private val mockSettingsRepository = mockk<SettingsRepository>()
    private lateinit var settingsViewModel: SettingsViewModel

    private lateinit var dynamicThemeFlow: MutableStateFlow<Boolean>
    private lateinit var themeFlow: MutableStateFlow<ThemeType>
    private lateinit var unitFlow: MutableStateFlow<UnitOfMeasurement>

    @BeforeEach
    fun setUp() {
        dynamicThemeFlow = MutableStateFlow(false)
        themeFlow = MutableStateFlow(ThemeType.System)
        unitFlow = MutableStateFlow(UnitOfMeasurement.Metric)

        every { mockSettingsRepository.isDynamicThemeEnabled() } returns dynamicThemeFlow
        every { mockSettingsRepository.getSelectedTheme() } returns themeFlow
        every { mockSettingsRepository.getSelectedMeasurementUnit() } returns unitFlow

        coEvery { mockSettingsRepository.updateDynamicTheme(any()) } just Runs
        coEvery { mockSettingsRepository.saveThemeType(any()) } just Runs
        coEvery { mockSettingsRepository.saveMeasurementUnitType(any()) } just Runs

        settingsViewModel = SettingsViewModel(mockSettingsRepository)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `GIVEN dynamic theme state and selected theme is system,selected measurement unit is metric THEN return correct settings ui model`(isDynamicThemeEnabled: Boolean) = runTest {
        // Act
        dynamicThemeFlow.emit(isDynamicThemeEnabled)

        // verify
        settingsViewModel.settingsUiModel.test {
            val expectedUiModel = getUiModel(
                isDynamicThemeEnabled = isDynamicThemeEnabled,
                selectedTheme = ThemeType.System,
                selectedUnit = UnitOfMeasurement.Metric,
            )
            awaitItem() shouldEqual expectedUiModel
            cancelAndIgnoreRemainingEvents()
        }
    }

    @ParameterizedTest
    @EnumSource(ThemeType::class)
    fun `GIVEN theme state and selected dynamic theme is false,selected measurement unit is metric THEN return correct settings ui model`(themeType: ThemeType) = runTest {
        // Act
        themeFlow.emit(themeType)

        // verify
        settingsViewModel.settingsUiModel.test {
            val expectedUiModel = getUiModel(
                isDynamicThemeEnabled = false,
                selectedTheme = themeType,
                selectedUnit = UnitOfMeasurement.Metric,
            )
            awaitItem() shouldEqual expectedUiModel
            cancelAndIgnoreRemainingEvents()
        }
    }

    @ParameterizedTest
    @EnumSource(UnitOfMeasurement::class)
    fun `GIVEN measurement unbit state and selected dynamic theme is false,selected theme is system THEN return correct settings ui model`(unitType: UnitOfMeasurement) = runTest {
        // Act
        unitFlow.emit(unitType)

        // verify
        settingsViewModel.settingsUiModel.test {
            val expectedUiModel = getUiModel(
                isDynamicThemeEnabled = false,
                selectedTheme = ThemeType.System,
                selectedUnit = unitType,
            )
            awaitItem() shouldEqual expectedUiModel
            cancelAndIgnoreRemainingEvents()
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `WHEN updateDynamicTheme is called THEN update dynamic theme`(isDynamicThemeEnabled: Boolean) {
        // Arrange
        val slot = slot<Boolean>()

        // Act
        settingsViewModel.updateDynamicTheme(isDynamicThemeEnabled)

        // Verify
        coVerify(exactly = 1) { mockSettingsRepository.updateDynamicTheme(capture(slot)) }
        slot.captured shouldEqual isDynamicThemeEnabled
    }

    @ParameterizedTest
    @EnumSource(ThemeType::class)
    fun `WHEN updateTheme is called THEN update theme`(themeType: ThemeType) {
        // Arrange
        val slot = slot<ThemeType>()

        // Act
        settingsViewModel.updateTheme(themeType)

        // Verify
        coVerify(exactly = 1) { mockSettingsRepository.saveThemeType(capture(slot)) }
        slot.captured shouldEqual themeType
    }

    @ParameterizedTest
    @EnumSource(UnitOfMeasurement::class)
    fun `WHEN updateUnitOfMeasurement is called THEN update unit of measurement`(unitOfMeasurement: UnitOfMeasurement) {
        // Arrange
        val slot = slot<UnitOfMeasurement>()

        // Act
        settingsViewModel.updateUnitOfMeasurement(unitOfMeasurement)

        // Verify
        coVerify(exactly = 1) { mockSettingsRepository.saveMeasurementUnitType(capture(slot)) }
        slot.captured shouldEqual unitOfMeasurement
    }

    private fun getUiModel(isDynamicThemeEnabled: Boolean, selectedTheme: ThemeType, selectedUnit: UnitOfMeasurement) = SettingsTestData.getSettingsUiModel(
        selectedTheme = selectedTheme,
        isDynamicThemeEnabled = isDynamicThemeEnabled,
        selectedMeasurementUnit = selectedUnit,
    )
}
