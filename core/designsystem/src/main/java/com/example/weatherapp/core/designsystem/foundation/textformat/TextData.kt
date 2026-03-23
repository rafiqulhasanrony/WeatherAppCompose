package com.example.weatherapp.core.designsystem.foundation.textformat

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString

abstract class TextData {

    @Composable
    abstract fun resolve(): String

    private data class RawText(
        val value: String,
    ) : TextData() {
        @Composable
        override fun resolve(): String = value
    }

    private data class ResourceText(
        @StringRes val resId: Int,
        val args: List<Any> = emptyList(),
    ) : TextData() {
        @Composable
        override fun resolve(): String {
            return stringResource(resId, *args.toTypedArray())
        }
    }

    private data class FormattedText(
        val template: String,
        val args: List<Any>,
    ) : TextData() {
        @Composable
        override fun resolve(): String {
            return String.format(template, *args.toTypedArray())
        }
    }

    data class AnnotatedText(
        val annotatedString: AnnotatedString,
    ) : TextData() {
        @Composable
        override fun resolve(): String = annotatedString.text
    }

    companion object {
        fun of(value: String): TextData = RawText(value = value)
        fun of(@StringRes id: Int, args: List<Any> = emptyList()): TextData = ResourceText(resId = id)
        fun of(template: String, args: List<Any>): TextData = FormattedText(template, args)
        fun of(annotatedString: AnnotatedString): TextData = AnnotatedText(annotatedString)
    }
}
