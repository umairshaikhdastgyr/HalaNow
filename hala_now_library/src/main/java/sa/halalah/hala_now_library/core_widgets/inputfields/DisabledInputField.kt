package com.hala.module_core.compose.inputfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hala.module_core.R
import com.hala.module_core.style.MyTypography

@Composable
fun DisabledInputField(
        modifier: Modifier = Modifier,
        text: String,
        labelText: String? = null,
        trailing: @Composable (() -> Unit)? = null
) {
    Column {
        labelText?.let { label ->
            Text(
                    text = label,
                    style = MaterialTheme.typography.subtitle1,
                    color = colorResource(id = R.color.text_5),
                    fontWeight = FontWeight.Normal,
                    modifier = modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
            )
        }
        TextField(
                value = text,
                onValueChange = {},
                enabled = false,
                textStyle = MyTypography.button.copy(fontWeight = FontWeight.Normal),
                modifier = Modifier
                        .fillMaxWidth()
                        .background(
                                shape = RoundedCornerShape(12.dp),
                                color = colorResource(id = R.color.overlay_4)
                        )
                        .border(
                                width = 1.dp,
                                color = colorResource(id = R.color.stroke_1),
                                shape = RoundedCornerShape(12.dp)
                        )
                        .focusable(false),
                colors = TextFieldDefaults.colors(
                        disabledTextColor = colorResource(id = R.color.text_6),
                        disabledContainerColor = colorResource(id = R.color.overlay_4),
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                ),
                trailingIcon = {
                    if (trailing != null) {
                        trailing()
                    }
                }

        )

    }
}

@Composable
@Preview
private fun DisabledInputFieldPreview() {
    DisabledInputField(
            text = "Essam Elnahdi",
            labelText = "Beneficiary name"
    )
}