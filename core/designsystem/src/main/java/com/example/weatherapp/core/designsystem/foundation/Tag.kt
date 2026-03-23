package com.example.weatherapp.core.designsystem.foundation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.core.designsystem.foundation.textformat.TextData
import com.example.weatherapp.core.designsystem.theme.Shapes
import com.example.weatherapp.core.designsystem.theme.Spacing

@Composable
fun Tag(tag: TextData) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = .8f),
                shape = Shapes.small,
            )
            .padding(
                start = Spacing.sm,
                end = Spacing.sm,
                top = Spacing.xs,
                bottom = Spacing.xs,
            ),

    ) {
        AppText(
            textData = tag,
        )
    }
}

@Preview
@Composable
fun TagPreview() {
    Tag(TextData.of("Dummy"))
}
