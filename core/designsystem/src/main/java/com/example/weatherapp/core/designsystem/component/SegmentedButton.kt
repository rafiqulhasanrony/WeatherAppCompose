package com.example.weatherapp.core.designsystem.component

import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.core.designsystem.foundation.AppText
import com.example.weatherapp.core.designsystem.foundation.textformat.TextData
import kotlin.collections.forEachIndexed

@Immutable
data class SegmentedItem<T>(
    val textData: TextData,
    val isSelected: Boolean = false,
    val type: T,
)

@Composable
fun <T> AppSegmentedButton(
    items: List<SegmentedItem<T>>,
    modifier: Modifier = Modifier,
    onSelectionChange: (Int, T) -> Unit,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier,
    ) {
        items.forEachIndexed { index, item ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = items.size,
                ),
                onClick = {
                    onSelectionChange(index, item.type)
                },
                selected = item.isSelected,
                label = {
                    AppText(
                        textData = item.textData,
                    )
                },
            )
        }
    }
}

@Preview()
@Composable
fun AppSegmentedButtonPreview() {
    AppSegmentedButton(
        items = listOf(
            SegmentedItem(TextData.of("Dark"), type = "dark"),
            SegmentedItem(TextData.of("Light"), true, type = "light"),
            SegmentedItem(TextData.of("System"), type = "System"),
        ),
        onSelectionChange = { _, _ -> },
    )
}
