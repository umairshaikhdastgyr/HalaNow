package sa.halalah.hala_now_library.core_widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import sa.halalah.hala_now_library.R

@Composable
fun HalaCircularProgressIndicator() {
    Dialog(
            onDismissRequest = {},
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                    contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        color = colorResource(id = R.color.primary_100),
                        strokeWidth = 6.dp,
                        strokeCap = StrokeCap.Round
                )
                CircularProgressIndicator(
                        modifier = Modifier
                                .size(24.dp)
                                .scale(scaleX = 1f, scaleY = -1f),
                        color = colorResource(id = R.color.neutrals_80),
                        strokeWidth = 6.dp,
                        strokeCap = StrokeCap.Round
                )
            }
        }
    }
}

@Preview
@Composable
private fun HalaCircularProgressIndicatorPreview() {
    HalaCircularProgressIndicator()
}