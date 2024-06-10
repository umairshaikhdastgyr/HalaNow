package sa.halalah.hala_now_library.core_widgets.inputfields

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.theme.MyTypography

@Composable
fun MobileInputField(
        inputValue: String,
        placeholder: String = "",
        errorText: String = "",
        validateMobileNumber: (String) -> Unit,
        onFocusChanged: (Boolean) -> Unit = {}

) {
    Column {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            Card(
                    colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.overlay_3)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
            ) {
                Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                                .padding(horizontal = 12.dp)
                ) {
                    Image(
                            painter = painterResource(id = R.drawable.saudi_flag),
                            contentDescription = ""
                    )
                    Text(
                            text = stringResource(R.string.mobile_number_prefix),
                            style = MyTypography.button,
                            color = colorResource(id = R.color.text_2),
                            modifier = Modifier.padding(start = 8.dp, end = 4.dp)
                    )
                    NoPaddingTextField(
                            value = inputValue,
                            onValueChange = {
                                validateMobileNumber(it)
                            },
                            placeholderText = placeholder.ifEmpty { stringResource(R.string.mobile_number_placeholder) },
                            keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                            ),
                        onFocusChanged = onFocusChanged
                    )
                }
            }
        }
        if (errorText.isNotEmpty()) {
        Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(18.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {

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

@Composable
@Preview
private fun MobileInputFieldPreview() {
    val inputValue = remember { "" }
    MobileInputField(
            inputValue = inputValue,
            placeholder = "59********",
            errorText = "Error",
            validateMobileNumber = {}
    )
}

@Composable()
@Preview(locale = "ar")
private fun MobileInputFieldArPreview() {
    val inputValue = remember { "" }
    MobileInputField(
        inputValue = inputValue,
        placeholder = "59********",
        errorText = "خطأ",
        validateMobileNumber = {}
    )
}
