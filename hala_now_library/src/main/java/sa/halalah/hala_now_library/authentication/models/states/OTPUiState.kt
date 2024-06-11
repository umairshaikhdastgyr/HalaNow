package sa.halalah.hala_now_library.authentication.models.states

import sa.halalah.hala_now_library.R


data class OTPUiState(
    val image: Int = R.drawable.ic_dashboard_otp_code,
    val title: Int = R.string.authentication_one_time_password,
    val body: Int = R.string.authentication_otp_description,
    val mobileNumber: String = "",
    val codeValue: String = "",
    val time: String = "00:00",
    val isTimeRunning: Boolean = true,
    val isMobileNumberChangeable: Boolean = false
)
