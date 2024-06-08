package com.hala.module_core.compose.inputfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.hala.module_core.R

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