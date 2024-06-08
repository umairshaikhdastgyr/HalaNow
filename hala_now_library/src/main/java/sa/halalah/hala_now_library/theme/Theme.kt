package sa.halalah.hala_now_library.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun HalaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = MyTypography,
        content = content,
    )
}