package sa.halalah.hala_now_library.core_widgets.inputfields

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.theme.MyTypography

@Composable
fun AmountTextField(
        modifier: Modifier = Modifier,
        amount: String,
        errorText: String = "",
        regex: Regex = "(\\d{0,6})|(\\d{0,6}\\.\\d{0,2})".toRegex(),
        onValueChange: (String) -> Unit
) {

    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
    ) {
        Text(
                text = stringResource(id = R.string.amount_field_enter_amount),
                color = colorResource(id = R.color.text_5),
                modifier = Modifier.padding(bottom = 12.dp),
                style = MyTypography.body1,
                fontWeight = FontWeight.Medium
        )

        Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
        ) {

            TextField(
                    value = amount,
                    onValueChange = {
                        if (it.matches(regex)) {
                            onValueChange(it)
                        }
                    },
                    modifier = modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                            focusedTextColor = colorResource(id = R.color.text_10),
                            unfocusedTextColor = colorResource(id = R.color.text_1),
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedPlaceholderColor = colorResource(id = R.color.text_6),
                            unfocusedPlaceholderColor = colorResource(id = R.color.text_6)
                    ),
                    textStyle = MyTypography.h3.copy(fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    visualTransformation = SuffixTransformation(stringResource(id = R.string.currency)),
            )
        }

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

class SuffixTransformation(private val suffix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val annotatedSuffix = buildAnnotatedString {
            append(text = text)
            append(suffix)
            addStyle(
                    style = SpanStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp),
                    start = text.length,
                    end = text.length + suffix.length
            )
        }

        val textWithSuffixMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (text.isEmpty()) return 0
                if (offset >= text.length) return text.length
                return offset
            }
        }

        return TransformedText(annotatedSuffix, textWithSuffixMapping)
    }
}

@Composable
@Preview(showBackground = true)
fun AmountTextFieldPreview() {
    var amount = "500"
    AmountTextField(
            amount = amount,
            onValueChange = {
                amount = it
            },
            errorText = "Some Error"
    )
}

