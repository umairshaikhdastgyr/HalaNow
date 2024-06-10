package com.hala.module_core.compose.attachments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.theme.MyTypography


@Composable
fun ImagesAttachmentViewer(
    attachments: List<Int>,
    label: String
) {
    Column {
        Text(
            text = label,
            style = MyTypography.subtitle1,
            fontWeight = FontWeight.Normal,
            color = colorResource(id = R.color.text_3)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(attachments) { attachment ->
                Image(
                    painter = painterResource(id = attachment),
                    contentDescription = "attachment",
                )
            }
        }
    }
}

@Preview
@Composable
private fun ShowImagesAttachmentPreview() {
    ImagesAttachmentViewer(
        label = "Attachment",
        attachments = listOf(
        )
    )
}

