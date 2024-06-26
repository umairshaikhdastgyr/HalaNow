package sa.halalah.hala_now_library.core_widgets.inputfields

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.theme.MyTypography

@Composable
fun NoPaddingTextField(
        value: String,
        onValueChange: (String) -> Unit,
        placeholderText: String,
        keyboardOptions: KeyboardOptions,
        onFocusChanged: (Boolean) -> Unit = {}
) {
    NoPaddingBasicTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            keyboardOptions = keyboardOptions,
            placeholder = {
                Text(
                        text = placeholderText,
                        color = colorResource(id = R.color.text_4),
                        style = MyTypography.button
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                    textColor = colorResource(id = R.color.text_2),
                    backgroundColor = colorResource(id = R.color.overlay_3),
                    placeholderColor = colorResource(id = R.color.text_3),
                    focusedIndicatorColor = colorResource(id = R.color.overlay_3),
                    unfocusedIndicatorColor = colorResource(id = R.color.overlay_3)
            ),
            textStyle = MyTypography.button,
            onFocusChanged = onFocusChanged
    )
}


@Composable
private fun NoPaddingBasicTextField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        readOnly: Boolean = false,
        textStyle: TextStyle = LocalTextStyle.current,
        label: @Composable (() -> Unit)? = null,
        placeholder: @Composable (() -> Unit)? = null,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        isError: Boolean = false,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions(),
        singleLine: Boolean = false,
        maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
        minLines: Int = 1,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        shape: Shape = TextFieldDefaults.TextFieldShape,
        colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
        onFocusChanged: (Boolean) -> Unit = { },
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))
    @OptIn(ExperimentalMaterialApi::class)
    BasicTextField(
            value = value,
            modifier = modifier
                    .background(colors.backgroundColor(enabled).value, shape)
                    .indicatorLine(enabled, isError, interactionSource, colors)
                    .defaultMinSize(
                            minWidth = TextFieldDefaults.MinWidth,
                            minHeight = TextFieldDefaults.MinHeight
                    )
                .onFocusChanged {
                    onFocusChanged(it.isFocused)
                },
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = mergedTextStyle,
            cursorBrush = SolidColor(colors.cursorColor(isError).value),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            decorationBox = @Composable { innerTextField: @Composable () -> Unit ->
                // places leading icon, text field with label and placeholder, trailing icon
                TextFieldDefaults.TextFieldDecorationBox(
                        value = value,
                        visualTransformation = visualTransformation,
                        innerTextField = innerTextField,
                        placeholder = placeholder,
                        label = label,
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        singleLine = singleLine,
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        contentPadding = PaddingValues(0.dp)
                )
            },

    )
}

@Preview
@Composable
fun HalaTextFieldPreview() {
    NoPaddingTextField(value = "", onValueChange = {}, placeholderText = "Enter Text Here", keyboardOptions = KeyboardOptions())
}
