package com.hala.module_core.compose.inputfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.hala.module_core.R
import com.hala.module_core.style.MyTypography

@Composable
fun KeyValueText(modifier: Modifier = Modifier,
                 key: String,
                 keyTextColor: Color = colorResource(id = R.color.text_3),
                 keyTextStyle: TextStyle = MyTypography.subtitle1,
                 value: String,
                 valueTextColor: Color = colorResource(id = R.color.text_1),
                 valueTextStyle: TextStyle = MyTypography.button.copy(fontWeight = FontWeight.SemiBold),
                 shouldForceValueLtr: Boolean = false) {
    Column(modifier = modifier) {
        Text(text = key, color = keyTextColor,
                style = keyTextStyle
        )
        if (shouldForceValueLtr) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Text(modifier = Modifier.padding(top = 6.dp),
                        text = value,
                        color = valueTextColor,
                        style = valueTextStyle
                )
            }
        } else {
            Text(modifier = Modifier.padding(top = 6.dp),
                    text = value,
                    color = valueTextColor,
                    style = valueTextStyle
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    KeyValueText(key = "This is a key", value = "this is a value")
}