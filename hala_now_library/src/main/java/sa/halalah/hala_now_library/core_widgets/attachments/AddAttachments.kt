package sa.halalah.hala_now_library.core_widgets.attachments

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.theme.MyTypography


@Composable
fun AddAttachments(
    label: String,
    isMultipleDocuments: Boolean = true,
    disablePickerButton: MutableState<Boolean> = mutableStateOf(false),
    attachments: List<Uri>,
    mimeTypes: List<MIMEType>,
    onAttachmentsAdded: (List<Uri>, Boolean) -> Unit,
    onAttachmentRemoved: (Uri) -> Unit
) {
    Column {
        Text(
                text = label,
                style = MyTypography.subtitle1,
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.text_3)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row(
                modifier = Modifier.fillMaxWidth()
        ) {
            AddAttachmentsButton(
                    label = label,
                    mimeTypes = mimeTypes,
                    isMultipleDocuments = isMultipleDocuments,
                    disablePickerButton = disablePickerButton,
                    onAttachmentsAdded = onAttachmentsAdded,
            )
            LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(start = 12.dp)
            ) {
                items(attachments) { attachment ->
                    AttachmentItem(
                            uri = attachment,
                            onRemoveFilePressed = { onAttachmentRemoved(attachment) }
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun AddAttachmentsPreview(
) {
    AddAttachments(
            attachments = emptyList(),
            label = "AddAttachment",
            onAttachmentsAdded = { _, _ -> },
            onAttachmentRemoved = { },
            mimeTypes = listOf(MIMEType.IMAGE)
    )
}

