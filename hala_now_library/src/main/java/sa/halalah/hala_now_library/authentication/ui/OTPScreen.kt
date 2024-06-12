package sa.halalah.hala_now_library.authentication.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import sa.halalah.hala_now_library.R
import sa.halalah.hala_now_library.authentication.models.states.OTPUiState
import sa.halalah.hala_now_library.authentication.models.states.OTPViewState
import sa.halalah.hala_now_library.authentication.view_models.BaseOTPCodeViewModel
import sa.halalah.hala_now_library.core_widgets.BackWidget
import sa.halalah.hala_now_library.core_widgets.HalaCircularProgressIndicator
import sa.halalah.hala_now_library.core_widgets.inputfields.CodeInputField
import sa.halalah.hala_now_library.utils.UIUtil


@Composable
fun OTPScreen(
    otpViewModel: BaseOTPCodeViewModel = viewModel(),
    mobileNumber: String,
    model: Any?,
    onDoneVerification: (data: Any?) -> Unit,
    onBackPressed: () -> Unit
) {
    val data by otpViewModel.data.collectAsState()
    val uiState by otpViewModel.uiState.collectAsState()

    val activity = LocalView.current.context as Activity
    activity.window.statusBarColor = ContextCompat.getColor(LocalContext.current, R.color.white)


    Column {
        if (uiState == OTPViewState.Loading)
            HalaCircularProgressIndicator()

        BackWidget(modifier = Modifier.padding(16.dp)) {
            onBackPressed()
        }
        OTPContent(
            content = data.copy(mobileNumber = mobileNumber),
            onCodeChange = {
                otpViewModel.onCodeChange(it)
            },
            onResendCodePressed = {
                otpViewModel.resendCode(model)
            },
            onChangeMobilePressed = {

            },
            onCodeFilled = {
                otpViewModel.onSubmitOtp(model)
            },
        )
    }

    LaunchedEffect(key1 = uiState) {
        when (val state = uiState) {
            is OTPViewState.Error -> {

                UIUtil.showError(activity, state.error)
            }

            is OTPViewState.SuccessMessage -> {
                UIUtil.showSuccess(
                    activity,
                    activity.resources.getString(R.string.authentication_otp_send_successfully)
                )
            }
            is OTPViewState.Data -> {

                onDoneVerification(state.data)
            }

            else -> {}
        }
    }
}

@Composable
private fun OTPContent(
    content: OTPUiState,
    onCodeChange: (String) -> Unit,
    onResendCodePressed: () -> Unit,
    onChangeMobilePressed: () -> Unit,
    onCodeFilled: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(id = content.image),
            contentDescription = "CodeImg",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = content.title),
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(id = R.color.text_1)
        )
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionCodeScreen(isMobileNumberChangeable = content.isMobileNumberChangeable,
            body = stringResource(id = content.body),
            mobileNumber = content.mobileNumber,
            onChangeMobile = {
                onChangeMobilePressed()
            })

        Spacer(modifier = Modifier.height(40.dp))
        CodeInputField(
            codeText = content.codeValue,
            isSecure = false,
            onCodeChange = { value, codeInputFilled ->
                onCodeChange(value)
                if (codeInputFilled) {
                    onCodeFilled()
                }
            },
        )
        Spacer(modifier = Modifier.height(34.dp))

        if (content.isTimeRunning) {
            CountDownContent(content.time)
        } else {
            Text(
                text = stringResource(id = R.string.authentication_resend_the_code),
                modifier = Modifier.clickable(onClick = {
                    onResendCodePressed()
                }),
                style = MaterialTheme.typography.subtitle1,
                color = colorResource(id = R.color.primary_100),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
private fun DescriptionCodeScreen(
    body: String,
    isMobileNumberChangeable: Boolean,
    mobileNumber: String,
    onChangeMobile: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val changeItStr = stringResource(id = R.string.authentication_change_it)
    //TODO If you have better solution please fix it 😭
    val mobileNo = "+966${mobileNumber}"

    val annotatedString = buildAnnotatedString {
        append("$body $mobileNo")
        if (isMobileNumberChangeable) {
            withStyle(style = SpanStyle(color = colorResource(id = R.color.primary_100))) {
                pushStringAnnotation(tag = changeItStr, annotation = changeItStr)
                append(changeItStr)
            }
        }
    }


    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        ClickableText(
            text = annotatedString,
            modifier = modifier.padding(start = 55.dp, end = 55.dp),
            style = MaterialTheme.typography.subtitle1.copy(
                color = colorResource(id = R.color.text_3),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                textDirection = TextDirection.Content

            ),
            onClick = { offset ->
                annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.let { span ->
                    onChangeMobile()
                }
            },
        )
    }
}

@Composable
private fun CountDownContent(time: String) {
    Row {
        Text(
            text = stringResource(id = R.string.authentication_code_will_be_sent_at),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.text_3)
        )
        Text(
            text = " $time",
            style = MaterialTheme.typography.subtitle1,
            color = colorResource(id = R.color.primary_100),
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview
@Composable
private fun OTPScreenPreview() {
    OTPContent(
        content = OTPUiState(codeValue = "", mobileNumber = "555555551"),
        onCodeChange = {},
        onResendCodePressed = {},
        onChangeMobilePressed = {},
        onCodeFilled = {},
    )
}

@Preview(locale = "ar")
@Composable
private fun OTPScreen_Ar_Preview() {
    OTPContent(
        content = OTPUiState(codeValue = "", mobileNumber = "555555551"),
        onCodeChange = {},
        onResendCodePressed = {},
        onChangeMobilePressed = {},
        onCodeFilled = {},
    )
}
