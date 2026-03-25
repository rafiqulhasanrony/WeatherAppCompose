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

@Immutable
data class SegmentedItem(
    val textData: TextData,
    val isSelected: Boolean = false,
)

@Composable
fun AppSegmentedButton(
    items: List<SegmentedItem>,
    modifier: Modifier = Modifier,
    onSelectionChange: (Int, SegmentedItem) -> Unit,
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
                    onSelectionChange(index, item)
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
            SegmentedItem(TextData.of("Dark")),
            SegmentedItem(TextData.of("Light"), true),
            SegmentedItem(TextData.of("System")),
        ),
        onSelectionChange = { _, _ -> },
    )
}
