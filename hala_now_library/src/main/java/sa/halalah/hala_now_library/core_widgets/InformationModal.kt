package sa.halalah.hala_now_library.core_widgets


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch
import sa.halalah.hala_now_library.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationModal(
    initState: Boolean = false,
    title: String,
    message: String = "",
    lottieJsonFile: Int = R.raw.hala_loading_spinner,
    btnText: String = "Done",
    onClose: () -> Unit = {},
    onPress: () -> Unit = {},
): (Boolean) -> Unit {
    val bottomSheetState = rememberModalBottomSheetState(initState)

    val scope = rememberCoroutineScope()
    val isShow = remember {
        mutableStateOf(initState)
    }
    if (isShow.value) {
        ModalBottomSheet(
            containerColor = colorResource(id = R.color.white),
            onDismissRequest = {
                isShow.value = false
                onClose()
            },
            sheetState = bottomSheetState,

            ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .safeDrawingPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                AnimateWithLottie(
                    lottieJsonFile,
                    Modifier.size((Size.width() / 2.5).dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
                )

                if (message != "") {
                    Text(
                        text = message,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.text_2),
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                }

                HalaButton(
                    state = true,
                    text = btnText,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(vertical = 20.dp),
                    onClick = {
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            isShow.value = false
                            onPress()
                        }
                    },
                )
            }
        }
    }

    return {
        isShow.value = it
    }
}

@Composable
fun AnimateWithLottie(
    lottieJsonFile: Int,
    modifier: Modifier = Modifier,
    onFinish: () -> Unit = {}
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            lottieJsonFile
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
            .size(100.dp)
    )



    if (preloaderProgress >= 1f) { // Works like onAnimationFinish listener
        onFinish()
    }

}


@Composable
@Preview(showBackground = true)
fun SuccessModalPreview() {
    InformationModal(true, "Title")
}