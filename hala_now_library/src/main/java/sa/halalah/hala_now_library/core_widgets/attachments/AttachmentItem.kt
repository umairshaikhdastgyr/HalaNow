package sa.halalah.hala_now_library.core_widgets.attachments

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import sa.halalah.hala_now_library.R


@Composable
fun AttachmentItem(
    uri: Uri,
    onRemoveFilePressed: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .width(92.dp)
            .height(92.dp)
            .background(
                color = colorResource(id = R.color.overlay_2),
                shape = RoundedCornerShape(size = 10.dp)
            ),
    ) {

        if (uri.isImage()) {
            ImageFromUri(uri = uri)
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_files),
                    contentDescription = "addAttachment",
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_cancel),
                contentDescription = "removeAttachment",
                modifier = Modifier
                    .padding(end = 6.dp, top = 6.dp)
                    .clickable(onClick = {
                        onRemoveFilePressed()
                    }),
            )
        }
    }

}

@Composable
fun ImageFromUri(uri: Uri) {
    val image = rememberAsyncImagePainter(model = uri)
    Image(painter = image, contentDescription = null)
}

@Composable
fun Uri.isImage() = getMimeTypeFromUri(this)?.split("/")?.first() == "image"


@Preview
@Composable
fun AttachmentItemPreview() {
    Column {
        AttachmentItem(
            uri = Uri.parse("Hello"),
            onRemoveFilePressed = {}
        )
        AttachmentItem(
            uri = Uri.parse("Hello"),
            onRemoveFilePressed = {}
        )
    }

}