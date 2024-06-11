package sa.halalah.hala_now_library.core_widgets.inputfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R


@Composable
fun CodeInputField(
    modifier: Modifier = Modifier,
    codeText: String,
    maxDigits: Int = 4,
    isSecure: Boolean,
    onCodeChange: (String, Boolean) -> Unit,
) {
    BasicTextField(modifier = modifier,
        value = TextFieldValue(codeText, selection = TextRange(codeText.length)),
        onValueChange = {
            if (it.text.length <= maxDigits) {
                onCodeChange(it.text, it.text.length == maxDigits)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = if (isSecure) PasswordVisualTransformation() else VisualTransformation.None,

        decorationBox = {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp, Alignment.CenterHorizontally
                    ),
                ) {
                    repeat(maxDigits) { index ->
                        CodeView(
                            index = index, text = codeText, isSecure = isSecure
                        )
                    }
                }
            }
        })
}

@Composable
private fun CodeView(
    index: Int, text: String, isSecure: Boolean
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> if (isSecure) "●" else text[index].toString()
    }
    Text(
        text = char,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body2,
        color = colorResource(id = R.color.neutrals_800),
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .focusable(isFocused)
            .width(65.dp)
            .height(61.dp)
            .background(
                color = colorResource(id = R.color.neutrals_50),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .border(
                2.dp, when {
                    isFocused -> colorResource(id = R.color.primary_100)
                    else -> colorResource(id = R.color.neutrals_50)
                }, RoundedCornerShape(12.dp)
            )
            .wrapContentHeight()

    )
}

@Preview
@Composable
fun CodeInputFieldPreview() {
    val otpValue = ""
    val pinValue = "5"
    Column {
        CodeInputField(
            codeText = otpValue,
            maxDigits = 6,
            isSecure = true,
            onCodeChange = { otpValue, otpInputFilled -> })
        Spacer(modifier = Modifier.height(20.dp))
        CodeInputField(
            codeText = pinValue,
            maxDigits = 4,
            isSecure = false,
            onCodeChange = { pinValue, pinInputFilled -> })
    }
}