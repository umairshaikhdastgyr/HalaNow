package sa.halalah.hala_now_library.authentication.view_models

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sa.halalah.business.ivr.data.models.request.RetryOTPRequestRemote
import sa.halalah.business.ivr.data.models.request.VerifyOTPRequestRemote
import sa.halalah.hala_now_library.authentication.models.states.OTPViewState
import sa.halalah.hala_now_library.authentication.repository.AuthRepo

class IntentOtpViewModel() : BaseOTPCodeViewModel() {

    private val authApiService = AuthRepo.authApiService

    override fun onSubmitOtp(request: Any?) {
        uiState.value = OTPViewState.Loading
        val map = request as HashMap<*, *>

        val requestPayload = VerifyOTPRequestRemote(map["reqId"].toString(), data.value.codeValue)

        viewModelScope.launch {
            val result =  authApiService.verifyOTP(requestPayload)
            if(result.isSuccessful){
                uiState.value = OTPViewState.Data(result.body())
            }else{
                uiState.value = OTPViewState.Error(result.errorBody().toString())
            }
        }
    }


    override fun resendCode(request: Any?) {
        super.resendCode(request)
        val map = request as HashMap<*, *>
        uiState.value = OTPViewState.Loading
        viewModelScope.launch {
            val retryOTPRequestRemote = RetryOTPRequestRemote(map["reqId"].toString())
            val result =  authApiService.retryOTP(retryOTPRequestRemote)

            if(result.isSuccessful){
                uiState.value = OTPViewState.SuccessMessage
            }else{
                uiState.value = OTPViewState.Error(result.errorBody().toString())
            }
        }
    }
}