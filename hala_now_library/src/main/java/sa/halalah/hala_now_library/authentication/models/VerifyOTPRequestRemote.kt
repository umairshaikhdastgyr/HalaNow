package sa.halalah.business.ivr.data.models.request

data class VerifyOTPRequestRemote(
    val reqId:String,
    val otpCode:String,
)
