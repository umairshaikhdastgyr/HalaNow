package com.hala.module_authentication.intent.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import sa.halalah.hala_now_library.authentication.ui.OTPScreen

@Composable
fun IntentWidgetCoordinator(
        mobileNumber: String,
        reqID: String,
        onCloseClicked: () -> Unit,
        onNext: () -> Unit
) {
    val map = remember {
        HashMap<Any, Any>()
    }
    map["reqId"] = reqID
    map["mobileNumber"] = mobileNumber

    OTPScreen(
        mobileNumber = mobileNumber,
        model = map,
        onDoneVerification = {
            onNext()
        },
        onBackPressed = {
            onCloseClicked()
        }
    )
}