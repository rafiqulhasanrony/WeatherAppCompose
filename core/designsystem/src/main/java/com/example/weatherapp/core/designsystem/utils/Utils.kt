package com.example.weatherapp.core.designsystem.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.weatherapp.core.designsystem.BuildConfig

fun Modifier.uiTestTag(tag: String): Modifier = if(BuildConfig.DEBUG) this.testTag(tag) else this
