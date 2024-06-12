package sa.halalah.hala_now_library.core_widgets.attachments

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R

@Composable
fun AddAttachmentsButton(
    mimeTypes: List<MIMEType>,
    isMultipleDocuments: Boolean,
    onAttachmentsAdded: (List<Uri>, Boolean) -> Unit,
    disablePickerButton: MutableState<Boolean>,
    label: String,
) {
    var isOptionsSheetShown by remember { mutableStateOf(false) }
    var isTakeAPhotoShown by remember { mutableStateOf(false) }
    var isPhotoPickerShown by remember { mutableStateOf(false) }
    var isFilePickerShown by remember { mutableStateOf(false) }

    Row(
            modifier = Modifier
                    .width(92.dp)
                    .height(92.dp)
                    .background(
                            color = colorResource(id = R.color.overlay_2),
                            shape = RoundedCornerShape(size = 10.dp)
                    )
                    .alpha(if (disablePickerButton.value) 0.4f else 1f)
                    .clickable {
                        if (!disablePickerButton.value)
                            isOptionsSheetShown = true
                    },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
                painter = painterResource(id = R.drawable.ic_add_image),
                contentDescription = "addAttachment",
        )
    }
    if (isOptionsSheetShown) {
        AttachmentOptionsPicker(
                label = label,
                takePhotoClicked = {
                    isOptionsSheetShown = false
                    isTakeAPhotoShown = true
                },
                uploadPhoto = {
                    isOptionsSheetShown = false
                    isPhotoPickerShown = true
                },
                chooseFileClicked = {
                    isOptionsSheetShown = false
                    isFilePickerShown = true
                },
                onDismiss = { isOptionsSheetShown = false }
        )
    }
    if (isTakeAPhotoShown) {
        TakeAPhoto(
                onSelect = {
                    onAttachmentsAdded(listOf(it), false)
                    isTakeAPhotoShown = false
                },
                onDismiss = {
                    isTakeAPhotoShown = false
                }
        )
    }
    if (isPhotoPickerShown) {
        FilePicker(
                mimeTypes = listOf(MIMEType.IMAGE),
                isMultipleDocuments = isMultipleDocuments,
                onFilesSelected = { selectedUris ->
                    onAttachmentsAdded(selectedUris, false)
                    isPhotoPickerShown = false
                },
                onDismiss = {
                    isPhotoPickerShown = false
                }
        )
    }
    if (isFilePickerShown) {
        FilePicker(
                mimeTypes = mimeTypes.filter { it != MIMEType.IMAGE },
                isMultipleDocuments = isMultipleDocuments,
                onFilesSelected = { selectedUris ->
                    onAttachmentsAdded(selectedUris, true)
                    isFilePickerShown = false
                },
                onDismiss = {
                    isFilePickerShown = false
                }
        )
    }
}

@Preview
@Composable
fun AddAttachmentsButtonPreview() {
    AddAttachmentsButton(
            mimeTypes = listOf(MIMEType.ANY),
            isMultipleDocuments = true,
            onAttachmentsAdded = { _, _ -> },
            disablePickerButton = remember { mutableStateOf(false) },
            label = "Add Attachment",
    )
}
