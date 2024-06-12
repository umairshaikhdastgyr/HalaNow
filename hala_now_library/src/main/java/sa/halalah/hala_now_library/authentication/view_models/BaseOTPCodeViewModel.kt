package sa.halalah.hala_now_library.authentication.view_models

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.halalah.hala_now_library.authentication.models.states.OTPUiState
import sa.halalah.hala_now_library.authentication.models.states.OTPViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import sa.halalah.business.ivr.data.models.request.RetryOTPRequestRemote
import sa.halalah.business.ivr.data.models.request.VerifyOTPRequestRemote
import sa.halalah.hala_now_library.authentication.models.TimerValues
import sa.halalah.hala_now_library.authentication.repository.AuthRepo
import sa.halalah.hala_now_library.utils.UIUtil
import sa.halalah.hala_now_library.utils.UtilCommon

open class BaseOTPCodeViewModel : ViewModel() {

    val data: MutableStateFlow<OTPUiState> = MutableStateFlow(
            OTPUiState(
                    mobileNumber = "+9601511123",
                    isMobileNumberChangeable = false)
    )
    private var timer: CountDownTimer? = null

    private val authApiService = AuthRepo.authApiService

    var uiState = MutableStateFlow<OTPViewState?>(null)
        private set

    init {
        startCounter()
    }

    fun onCodeChange(newCode: String) {
        data.value = data.value.copy(codeValue = newCode)
    }

    private fun startCounter() {
        data.value = data.value.copy(isTimeRunning = true)
        timer = object : CountDownTimer(TimerValues.COUNTDOWN_TIME, TimerValues.ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                val time = DateUtils.formatElapsedTime(millisUntilFinished / TimerValues.ONE_SECOND)
                data.value = data.value.copy(time = time)
            }

            override fun onFinish() {
                data.value = data.value.copy(isTimeRunning = false)
            }
        }
        timer?.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    fun clearState() {
        uiState.value = null
        data.value = data.value.copy(codeValue = "")
    }

     fun onSubmitOtp(request: Any?) {
        uiState.value = OTPViewState.Loading
        val map = request as HashMap<*, *>

        val requestPayload = VerifyOTPRequestRemote(map["reqId"].toString(), data.value.codeValue)

        viewModelScope.launch {
            val result =  authApiService.verifyOTP(requestPayload)
            if(result.isSuccessful){
                uiState.value = OTPViewState.Data(result.body())
            }else{
                uiState.value = OTPViewState.Error(UtilCommon.parseErrorMessage(result.errorBody()))
            }
        }
    }


      fun resendCode(request: Any?) {
        val map = request as HashMap<*, *>
        uiState.value = OTPViewState.Loading
        viewModelScope.launch {
            val retryOTPRequestRemote = RetryOTPRequestRemote(map["reqId"].toString())
            val result =  authApiService.retryOTP(retryOTPRequestRemote)

            if(result.isSuccessful){
                uiState.value = OTPViewState.SuccessMessage
                startCounter()
            }else{
                uiState.value = OTPViewState.Error(UtilCommon.parseErrorMessage(result.errorBody()))
            }
        }
    }

}