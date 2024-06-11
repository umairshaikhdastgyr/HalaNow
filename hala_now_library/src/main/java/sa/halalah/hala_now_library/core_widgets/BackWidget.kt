package sa.halalah.hala_now_library.core_widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R

/**
 * A widget to navigate back to the previous screen
 * @param modifier
 * @param title an optional parameter in case you need a title to be displayed in the middle
 * @param onBackClicked handle navigation logic
 * */
@Composable
fun BackWidget(
        modifier: Modifier = Modifier,
        title: String = "",
        onBackClicked: () -> Unit
) {
    Row(
            modifier = modifier
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
                painter = painterResource(id = R.drawable.back_arrow_new),
                contentDescription = stringResource(
                        id = R.string.content_description_back_button
                ), modifier = Modifier
                .size(24.dp)
                .clickable {
                    onBackClicked()
                }
        )
        Text(
                text = title,
                style = MaterialTheme.typography.button.copy(
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                )
        )
        Box {}
    }
}


@Preview
@Composable
private fun BackWidgetPreviewEn() {
    BackWidget(modifier = Modifier.padding(16.dp), title = "Title") {

    }
}

@Preview(locale = "ar")
@Composable
private fun BackWidgetPreviewAr() {
    BackWidget(modifier = Modifier.padding(16.dp)) {

    }
}