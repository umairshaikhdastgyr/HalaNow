package sa.halalah.hala_now_library.core_widgets.inputfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import sa.halalah.hala_now_library.R

@Composable
fun NoBackgroundTextField(
    placeholder: String,
    value : String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = {
            onValueChanged(it)
        },
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(placeholder, color = colorResource(id = R.color.text_3)) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = colorResource(id = R.color.text_2),
            placeholderColor = colorResource(id = R.color.text_3),
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}