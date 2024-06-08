package sa.halalah.hala_now_library.authentication.models

import com.google.gson.annotations.SerializedName

data class IntentAuthenticationResponseRemote(
        @SerializedName("reqId") val reqId: String,
        @SerializedName(value = "requiresOTP", alternate = ["requireOtp"]) val requiresOTP: Boolean,
        @SerializedName(value = "requiresPIN", alternate = ["requirePIN"]) val requirePIN: Boolean,
        @SerializedName(value = "requiresIVR", alternate = ["requireIvr"]) val requiresIVR: Boolean,

        @SerializedName(value = "isOtpVerified", alternate = ["otpVerified"]) val isOtpVerified: Boolean,
        @SerializedName(value = "isPINVerified", alternate = ["pinVerified"]) val isPINVerified: Boolean,
        @SerializedName(value = "isIvrVerified", alternate = ["ivrVerified"]) val isIvrVerified: Boolean,

        @SerializedName("isPinCreated") val isPinCreated: Boolean,
        @SerializedName("mobileNumber") val mobileNumber: String? = "",
        @SerializedName("agentMobileNumber") val agentMobileNumber: String? = "",
        @SerializedName("isIntentAuthCompleted") val isIntentAuthCompleted: Boolean
)