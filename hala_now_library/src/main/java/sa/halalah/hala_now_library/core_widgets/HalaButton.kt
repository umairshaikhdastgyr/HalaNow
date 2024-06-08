package sa.halalah.hala_now_library.core_widgets

import androidx.compose.foundation.Image
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.theme.MyTypography

@Composable
fun HalaButton(
        text: String,
        state: Boolean,
        image: Painter? = null,
        modifier: Modifier = Modifier,
        onClick: () -> Unit
) {

    val animatedButtonColor = animateColorAsState(
        targetValue = if (state) colorResource(id = R.color.primary_100) else colorResource(id = R.color.neutrals_50),
        animationSpec = tween(200, 0, LinearOutSlowInEasing), label = "animatedButtonColor"
    )
    val animatedTextColor = animateColorAsState(
        targetValue = if (state) colorResource(id = R.color.neutrals_10) else
            colorResource(id = R.color.text_3),
        animationSpec = tween(200, 0, LinearOutSlowInEasing), label = "animatedTextColor"
    )
    var enableAgain by remember { mutableStateOf(true) } //Prevent double click
    LaunchedEffect(enableAgain, block = {
        if (enableAgain) return@LaunchedEffect
        delay(timeMillis = 500L)
        enableAgain = true
    })
    Button(
            onClick = {
                if (enableAgain) {
                    enableAgain = false
                    onClick()
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(24),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = animatedButtonColor.value,
                    contentColor = colorResource(id = R.color.neutrals_10),
                disabledBackgroundColor = animatedButtonColor.value,
                    disabledContentColor = colorResource(id = R.color.neutrals_300)
            ),
            enabled = state,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = text,
            style = MyTypography.button.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = if (state) animatedTextColor.value else animatedTextColor.value
        )
        if(image != null) Image(painter = image, contentDescription = "", modifier = Modifier
            .padding(top= 10.dp)
            .size(20.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
fun HalaButtonPreview() {
    HalaButton(text = "Next", state = true, image= painterResource(id = R.drawable.back_arrow_new)) {}
}