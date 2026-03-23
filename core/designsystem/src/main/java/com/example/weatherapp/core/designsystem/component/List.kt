package com.example.weatherapp.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.core.designsystem.foundation.AppText
import com.example.weatherapp.core.designsystem.foundation.Tag
import com.example.weatherapp.core.designsystem.theme.Spacing

object AppList

class ListTitleProperties(
    val title: String,
    val subtitle: String? = null,
)

@Composable
fun AppList.TitleWithTag(modifier: Modifier = Modifier, listTitleProperties: ListTitleProperties) {
    BaseListItem(
        modifier = modifier,
        leadingContent = {
            AppText(
                text = listTitleProperties.title,
            )
        },
        trailingContent = {
            listTitleProperties.subtitle?.let {
                Tag(
                    tag = it,
                )
            }
        },
    )
}

@Composable
fun AppList.TitleWithSwitch(
    modifier: Modifier = Modifier,
    listTitleProperties: ListTitleProperties,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,

) {
    BaseListItem(
        modifier = modifier,
        leadingContent = {
            AppText(
                text = listTitleProperties.title,
            )
        },
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        },
    )
}

@Preview
@Composable
fun ListTTile() {
    AppList.TitleWithTag(
        listTitleProperties = ListTitleProperties("Title", "Subtitle"),
    )
}

@Composable
private fun BaseListItem(
    modifier: Modifier = Modifier,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    val clickableModifier = if (onClick != null) {
        Modifier.clickable { onClick() }
    } else {
        Modifier
    }

    Row(
        modifier = modifier
            .then(clickableModifier)
            .background(
                color = MaterialTheme.colorScheme.surface,
            )
            .fillMaxWidth()
            .padding(Spacing.sm),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingContent?.invoke()

        trailingContent?.invoke()
    }
}
