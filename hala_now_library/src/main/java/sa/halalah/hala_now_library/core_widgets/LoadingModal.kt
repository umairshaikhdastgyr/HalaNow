package sa.halalah.hala_now_library.core_widgets


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog

@Composable
fun loadingModal(
    initState: Boolean = false,
): (Boolean) -> Unit {
    val isShow = remember {
        mutableStateOf(initState)
    }
    if (isShow.value) {
        Dialog(
            onDismissRequest = { },
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HalaCircularProgressIndicator()
            }
        }
    }

    return {
        isShow.value = it
    }
}


@Composable
@Preview(showBackground = true)
fun LoadingModalPreview() {
    loadingModal(true)
}