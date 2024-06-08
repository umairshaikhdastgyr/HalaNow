package sa.halalah.hala_now_library.core_widgets.inputfields

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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.theme.MyTypography


@Composable
fun InputField(
        modifier: Modifier = Modifier,
        text: String,
        textStyle: TextStyle = MyTypography.button.copy(
                fontWeight = FontWeight.Normal,
                platformStyle = PlatformTextStyle(
                        includeFontPadding = true
                )
        ),
        labelText: String? = null,
        placeholderText: String,
        errorText: String = "",
        trailingIcon: Int? = null,
        leadingIcon: Int? = null,
        prefix: String? = null,
        readOnly: Boolean = false,
        singleLine: Boolean = true,
        onValueChange: (String) -> Unit,
        onInputFieldPressed: () -> Unit = {},
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions()
) {
    if (leadingIcon != null) {
        InputFieldWithLeadingIcon(
                text = text,
                textStyle = textStyle,
                labelText = labelText,
                errorText = errorText,
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon,
                placeholderText = placeholderText,
                readOnly = readOnly,
                onInputFieldPressed = onInputFieldPressed,
                keyboardActions = keyboardActions,
                keyboardOptions = keyboardOptions,
                modifier = modifier,
                onValueChange = onValueChange
        )
    } else {
        Column {
            labelText?.let { label ->
                Text(
                        text = label,
                        style = MaterialTheme.typography.subtitle1,
                        color = colorResource(id = R.color.text_3),
                        fontWeight = FontWeight.Normal,
                        modifier = modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                )
            }
            TextField(
                    modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (readOnly) {
                                    onInputFieldPressed()
                                }
                            },
                    enabled = !readOnly,
                    readOnly = readOnly,
                    singleLine = singleLine,
                    value = text,
                    prefix = {
                        prefix.let {
                            if (it != null) {
                                Text(
                                        text = it,
                                        style = textStyle,
                                        color = colorResource(id = R.color.text_2)
                                )
                            }
                        }
                    },
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
                    colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = colorResource(id = R.color.overlay_3),
                            unfocusedContainerColor = colorResource(id = R.color.overlay_3),
                            disabledContainerColor = colorResource(id = R.color.overlay_3),
                            cursorColor = colorResource(id = R.color.primary_100),
                            focusedTextColor = colorResource(id = R.color.text_2),
                            unfocusedTextColor = colorResource(id = R.color.text_2),
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

}


@Preview
@Composable
private fun InputFieldPreview() {
    InputField(
            text = "",
            labelText = "National Id",
            placeholderText = "Enter your national ID",
            onValueChange = {},
            errorText = "ErrorText",
            keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {}),
    )
}

@Preview
@Composable
private fun ClickableInputFieldPreview() {
    InputField(
            text = "",
            labelText = "Date of Birth",
            placeholderText = "DD/MM/YYYY",
            onValueChange = {},
            errorText = "",
            trailingIcon = R.drawable.close_icon,
            readOnly = true
    )
}

@Preview
@Composable
private fun LeadingIconInputFieldPreview() {
    InputField(
            text = "حسابي الشخصي",
            labelText = "Dummy",
            leadingIcon = R.drawable.close_icon,
            placeholderText = "Dummy",
            onValueChange = {},
            errorText = "",
            keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {}),
    )
}