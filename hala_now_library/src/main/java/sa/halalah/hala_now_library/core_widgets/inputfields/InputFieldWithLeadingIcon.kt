package com.hala.module_core.compose.inputfields

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hala.module_core.R

@Composable
fun InputFieldWithLeadingIcon(
        modifier: Modifier = Modifier,
        text: String,
        textStyle: TextStyle,
        labelText: String? = null,
        placeholderText: String,
        errorText: String = "",
        trailingIcon: Int? = null,
        leadingIcon: Int? = null,
        readOnly: Boolean = false,
        singleLine : Boolean = true,
        onValueChange: (String) -> Unit,
        onInputFieldPressed: () -> Unit = {},
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions(),
) {
    Column {
        labelText?.let { label ->
            Text(
                    text = label,
                    style = MaterialTheme.typography.subtitle1,
                    color = colorResource(id = R.color.text_3),
                    fontWeight = FontWeight.Normal,
                    modifier = modifier.fillMaxWidth()
            )
        }
        TextField(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable {
                            if (readOnly) {
                                onInputFieldPressed()
                            }
                        },
                enabled = !readOnly,
                readOnly = readOnly,
                singleLine = singleLine,
                value = text,
                textStyle = textStyle,
                placeholder = {
                    Text(
                            text = placeholderText,
                            color = colorResource(id = R.color.text_4),
                            style = textStyle
                    )
                },
                onValueChange = onValueChange,
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    trailingIcon?.let { trailingIcon ->
                        Image(
                                painter = painterResource(id = trailingIcon),
                                contentDescription = "trailingIcon"
                        )
                    }
                },
                leadingIcon = {
                    leadingIcon?.let { trailingIcon ->
                        Image(
                                painter = painterResource(id = trailingIcon),
                                contentDescription = "trailingIcon"
                        )
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = colorResource(id = R.color.overlay_3),
                        cursorColor = colorResource(id = R.color.primary_100),
                        textColor = colorResource(id = R.color.text_2),
                        disabledTextColor = colorResource(id = R.color.text_2),
                ),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
        )
        Row(
                modifier = Modifier
                        .padding(top = 8.dp)
                        .height(18.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
            if (errorText.isNotEmpty()) {
                Image(
                        painter = painterResource(id = R.drawable.ic_warning),
                        contentDescription = "errorIcon",
                )
                Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = errorText,
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.error_100)
                )
            }

        }
    }

}