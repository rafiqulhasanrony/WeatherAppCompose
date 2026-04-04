package com.example.settings.di

import com.example.settings.fake.FakeSettingsRepository
import com.example.weatherapp.feature.settings.di.SettingsDataModule
import com.example.weatherapp.feature.settings.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import jakarta.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [SettingsDataModule::class],
)
object TestSettingsModule {

    @Provides
    @Singleton
    fun provideFakeSettingsRepository(): SettingsRepository = FakeSettingsRepository()
}
