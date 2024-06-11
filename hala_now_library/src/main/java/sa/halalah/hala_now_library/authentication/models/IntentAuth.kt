package sa.halalah.hala_now_library.authentication.models

data class IntentAuth(
    val reqId: String,
    val requiresOTP: Boolean,
    val requiresIVR: Boolean,
    val isOtpVerified: Boolean,
    val requirePIN: Boolean,
    val isPINVerified: Boolean,
    val isIvrVerified: Boolean,
    val isPinCreated: Boolean,
    val mobileNumber: String? = "",
    val agentMobileNumber: String? = "",
    val isIntentAuthCompleted: Boolean
)