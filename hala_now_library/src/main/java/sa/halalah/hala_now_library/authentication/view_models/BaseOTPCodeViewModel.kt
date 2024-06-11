package sa.halalah.hala_now_library.authentication.view_models

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.ViewModel
import sa.halalah.hala_now_library.authentication.models.states.OTPUiState
import sa.halalah.hala_now_library.authentication.models.states.OTPViewState
import kotlinx.coroutines.flow.MutableStateFlow
import sa.halalah.hala_now_library.authentication.models.TimerValues

open class BaseOTPCodeViewModel : ViewModel() {

    val data: MutableStateFlow<OTPUiState> = MutableStateFlow(
            OTPUiState(
                    mobileNumber = "+9601511123",
                    isMobileNumberChangeable = false)
    )
    private var timer: CountDownTimer? = null


    var uiState = MutableStateFlow<OTPViewState?>(null)
        private set

    init {
        startCounter()
    }

    fun onCodeChange(newCode: String) {
        data.value = data.value.copy(codeValue = newCode)
    }

    fun startCounter() {
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

    open fun onSubmitOtp(request: Any?) {
    }


    open fun resendCode(request: Any?) {
        startCounter()
    }

}