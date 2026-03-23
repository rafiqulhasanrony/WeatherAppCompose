package com.example.weatherapp.core.designsystem.foundation

import androidx.annotation.FloatRange
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    color: Color = Color.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    @FloatRange(from = 0.0, to = 1.0) alpha: Float = 1F,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        modifier = modifier
            .alpha(alpha),
        color = color,
        overflow = overflow,
        maxLines = maxLines,
        minLines = minLines,
        style = textStyle,
        textAlign = textAlign,
    )
}
