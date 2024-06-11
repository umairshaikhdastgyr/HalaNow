package sa.halalah.hala_now_library.authentication.models.states

sealed class OTPViewState {
    data object Loading : OTPViewState()
    data class Error(val error: String) : OTPViewState()
    data class Data(val data: Any?) : OTPViewState()

    data object SuccessMessage : OTPViewState()
}