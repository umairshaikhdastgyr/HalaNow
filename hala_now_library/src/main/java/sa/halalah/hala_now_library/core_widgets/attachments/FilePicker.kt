package sa.halalah.hala_now_library.core_widgets.attachments

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext


@Composable
fun FilePicker(
    mimeTypes: List<MIMEType>,
    isMultipleDocuments: Boolean,
    onFilesSelected: (List<Uri>) -> Unit,
    onDismiss: () -> Unit
) {
    val pickFileLauncher =
            if (isMultipleDocuments)
                rememberLauncherForActivityResult(
                        ActivityResultContracts.OpenMultipleDocuments()
                ) { uris ->
                    if (uris.isNotEmpty())
                        onFilesSelected(uris)
                    else
                        onDismiss()

                } else {
                rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
                    if (uri != null)
                        onFilesSelected(listOf(uri))
                    else
                        onDismiss()
                }
            }
    SideEffect {
        pickFileLauncher.launch(mimeTypes.map { it.type }.toTypedArray())
    }
}

@Composable
fun getMimeTypeFromUri(uri: Uri): String? {
    val contentResolver = LocalContext.current.contentResolver
    return contentResolver.getType(uri)
}


