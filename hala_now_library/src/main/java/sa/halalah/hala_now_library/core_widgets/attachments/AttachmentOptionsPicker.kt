package sa.halalah.hala_now_library.core_widgets.attachments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.theme.MyTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttachmentOptionsPicker(
        label: String,
        takePhotoClicked: () -> Unit,
        uploadPhoto: () -> Unit,
        chooseFileClicked: () -> Unit,
        onDismiss: () -> Unit
) {
    ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            containerColor = colorResource(id = R.color.overlay_5),
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {

        Column(
                modifier = Modifier.padding(16.dp)
        ) {
            Text(
                    modifier = Modifier
                            .fillMaxWidth(),
                    text = label,
                    style = MyTypography.body2,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.text_1)

            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                    modifier = Modifier
                            .clickable {
                                takePhotoClicked()
                            }
                            .fillMaxWidth()
                            .background(
                                    color = colorResource(id = R.color.overlay_2),
                                    shape = RoundedCornerShape(24.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                        painter = painterResource(id = R.drawable.ic_take_a_photo),
                        contentDescription = ""
                )

                Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(id = R.string.take_a_photo_option),
                        style = MyTypography.body2,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.text_2)

                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                    modifier = Modifier
                            .clickable {
                                uploadPhoto()
                            }
                            .fillMaxWidth()
                            .background(
                                    color = colorResource(id = R.color.overlay_2),
                                    shape = RoundedCornerShape(24.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                        painter = painterResource(id = R.drawable.ic_upload_from_photos),
                        contentDescription = ""
                )
                Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(id = R.string.upload_a_photo_option),
                        style = MyTypography.body2,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.text_2)

                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                    modifier = Modifier
                            .clickable {
                                chooseFileClicked()
                            }
                            .fillMaxWidth()
                            .background(
                                    color = colorResource(id = R.color.overlay_2),
                                    shape = RoundedCornerShape(24.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                        painter = painterResource(id = R.drawable.ic_choose_from_files),
                        contentDescription = ""
                )
                Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(id = R.string.choose_a_file_option),
                        style = MyTypography.body2,
                        fontWeight = FontWeight.Normal,
                        color = colorResource(id = R.color.text_2)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}