package sa.halalah.hala_now_library.core_widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.theme.MyTypography

data class TitleBarIcons(val id: Int, val drawableId: Int, val callback: (id: Int) -> Unit = {})

@Composable
fun TitleBar(title: String, isTitleInLine: Boolean = true, verticalPadding: Dp = 24.dp, horizontalPadding: Dp = 16.dp, iconsList: List<TitleBarIcons> = emptyList(), backButtonOnClick: () -> Unit) {
    Column {

        Row(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp), verticalAlignment = Alignment.CenterVertically

        ) {
            Image(painter = painterResource(id = R.drawable.back_arrow_new), contentDescription = "", modifier = Modifier
                    .clickable {
                        backButtonOnClick()
                    }
                    .padding(vertical = verticalPadding, horizontal = horizontalPadding))

            if (isTitleInLine)
                Text(text = title, style = MyTypography.body1, color = colorResource(id = R.color.text_1), fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.weight(1f))
            if (iconsList.isNotEmpty()) {
                for (icon in iconsList) {
                    Image(painter = painterResource(id = icon.drawableId), contentDescription = null, modifier = Modifier
                            .padding(start = 6.dp)
                            .clickable { icon.callback(icon.id) })
                }
            }

        }

        if (!isTitleInLine) {
            Text(text = title, style = MyTypography.h5, color = colorResource(id = R.color.text_1), fontWeight = FontWeight.Bold, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TitleBarPreview() {
    TitleBar(title = "Main Title", backButtonOnClick = {})
}

@Composable
@Preview(showBackground = true)
fun TitleBarPreview2() {
    TitleBar(title = "Main Title", isTitleInLine = false, backButtonOnClick = {})
}