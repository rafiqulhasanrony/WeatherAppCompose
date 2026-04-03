package com.example.settings.data.data

import androidx.datastore.preferences.core.Preferences.Key
import app.cash.turbine.test
import com.example.baseapp.core.datastore.PreferenceDataStoreAPI
import com.example.baseapp.core.testing.shouldEqual
import com.example.weatherApp.feature.settings.publicApi.datastore.SettingsDataStoreKeys
import com.example.weatherApp.feature.settings.publicApi.model.ThemeType
import com.example.weatherApp.feature.settings.publicApi.model.UnitOfMeasurement
import com.example.weatherapp.feature.settings.data.preference.SettingsPreferenceDataSourceImpl
import com.example.weatherapp.feature.settings.domain.preference.SettingsPreferenceDataSource
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

class SettingsPreferenceDataSourceTest {
    private val mockPreferenceDataStoreAPI = mockk<PreferenceDataStoreAPI>()
    private lateinit var sutSettingsDataSource: SettingsPreferenceDataSource

    @BeforeEach
    fun setup() {
        sutSettingsDataSource = SettingsPreferenceDataSourceImpl(mockPreferenceDataStoreAPI)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `GIVEN dynamic theme state THEN emit correct value`(isEnabled: Boolean) = runTest {
        // Arrange
        every {
            mockPreferenceDataStoreAPI.getPreference(SettingsDataStoreKeys.DYNAMIC_THEME_PREF_KEY, any(Boolean::class))
        } returns flowOf(isEnabled)

        // Act and verify
        sutSettingsDataSource.isDynamicThemeEnabled().test {
            assertEquals(isEnabled, awaitItem())
            awaitComplete()
        }
    }

    @ParameterizedTest
    @EnumSource(ThemeType::class)
    fun `GIVEM selected theme type THEN emit correct theme type`(selectedTheme: ThemeType) = runTest {
        // Arrange
        every {
            mockPreferenceDataStoreAPI.getPreference(SettingsDataStoreKeys.THEME_PREF_KEY, any(String::class))
        } returns flowOf(selectedTheme.name)

        // Act and verify
        sutSettingsDataSource.getSelectedTheme().test {
            assertEquals(selectedTheme, awaitItem())
            awaitComplete()
        }
    }

    @ParameterizedTest
    @EnumSource(UnitOfMeasurement::class)
    fun `GIVEN selected measurement type THEN emit correct measurement type`(selectedMeasurement: UnitOfMeasurement) = runTest {
        // Arrange
        every {
            mockPreferenceDataStoreAPI.getPreference(SettingsDataStoreKeys.MEASUREMENT_UNIT_PREF_KEY, any(String::class))
        } returns flowOf(selectedMeasurement.name)

        // Act and verify
        sutSettingsDataSource.getSelectedMeasurementUnit().test {
            assertEquals(selectedMeasurement, awaitItem())
            awaitComplete()
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `WHEN save dynamic theme THEN save correct value`(isEnabled: Boolean) = runTest {
        // Arrange
        val key = slot<Key<Boolean>>()
        val value = slot<Boolean>()
        coEvery {
            mockPreferenceDataStoreAPI.putPreference(any<Key<Boolean>>(), any<Boolean>())
        } just runs

        // Act
        sutSettingsDataSource.saveDynamicTheme(isEnabled)

        // Verify
        coVerify(exactly = 1) { mockPreferenceDataStoreAPI.putPreference(capture(key), capture(value)) }
        key.captured shouldEqual SettingsDataStoreKeys.DYNAMIC_THEME_PREF_KEY
        value.captured shouldEqual isEnabled
        confirmVerified(mockPreferenceDataStoreAPI)
    }

    @ParameterizedTest
    @EnumSource(ThemeType::class)
    fun `WHEN save theme type THEN save correct value`(themeType: ThemeType) = runTest {
        // Arrange
        val key = slot<Key<String>>()
        val value = slot<String>()
        coEvery { mockPreferenceDataStoreAPI.putPreference(any<Key<String>>(), any<String>()) } returns Unit

        // Act
        sutSettingsDataSource.saveTheme(themeType)

        // Verify
        coVerify(exactly = 1) { mockPreferenceDataStoreAPI.putPreference(capture(key), capture(value)) }
        key.captured shouldEqual SettingsDataStoreKeys.THEME_PREF_KEY
        value.captured shouldEqual themeType.name
        confirmVerified(mockPreferenceDataStoreAPI)
    }

    @ParameterizedTest
    @EnumSource(UnitOfMeasurement::class)
    fun `WHEN save measurement type THEN save correct value`(unitOfMeasurement: UnitOfMeasurement) = runTest {
        // Arrange
        val key = slot<Key<String>>()
        val value = slot<String>()
        coEvery { mockPreferenceDataStoreAPI.putPreference(any<Key<String>>(), any<String>()) } just runs

        // Act
        sutSettingsDataSource.saveMeasurementUnit(unitOfMeasurement)

        // Verify
        coVerify(exactly = 1) { mockPreferenceDataStoreAPI.putPreference(capture(key), capture(value)) }
        key.captured shouldEqual SettingsDataStoreKeys.MEASUREMENT_UNIT_PREF_KEY
        value.captured shouldEqual unitOfMeasurement.name
    }
}
