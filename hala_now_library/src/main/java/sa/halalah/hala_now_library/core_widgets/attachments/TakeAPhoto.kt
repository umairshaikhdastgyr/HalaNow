package sa.halalah.hala_now_library.core_widgets.attachments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun TakeAPhoto(
        onSelect: (Uri) -> Unit,
        onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
            Objects.requireNonNull(context),
            context.packageName + ".provider", file
    )

    val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { success ->
                if (success)
                    onSelect(uri)
                else
                    onDismiss()
            }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { granted ->
                if (granted)
                    cameraLauncher.launch(uri)
                else
                    onDismiss()
            }
    )

    val cameraPermissionState = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)


    SideEffect {
        if (cameraPermissionState == PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(uri)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    return File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            externalCacheDir /* directory */
    )
}