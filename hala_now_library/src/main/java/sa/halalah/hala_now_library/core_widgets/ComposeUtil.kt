package sa.halalah.hala_now_library.core_widgets

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

object Size {
    @Composable
    fun height(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenHeightDp
    }

    @Composable
    fun width(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenWidthDp
    }




    @Composable
    fun width(cover: Double): Int {
        val configuration = LocalConfiguration.current
        return (configuration.screenWidthDp * cover).toInt()
    }
}


fun copyToClipboard(context: Context, text: String) {
    val clipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("password", text)
    clipboardManager.setPrimaryClip(clip)
}