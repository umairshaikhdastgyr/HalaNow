package sa.halalah.hala_now_library.authentication.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.halalah.hala_now_library.authentication.models.states.IntentViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import sa.halalah.hala_now_library.authentication.models.IntentAuthenticationResponseRemote
import sa.halalah.hala_now_library.authentication.repository.AuthRepo
import sa.halalah.hala_now_library.utils.UtilCommon

class IntentViewModel(

) : ViewModel() {

    var uiState = MutableStateFlow<IntentViewState?>(null)
        private set

    val INTERVAL_TIME: Long = 2500

    private val authApiService = AuthRepo.authApiService

    fun checkRequestStatus(reqId: String, isPolling: Boolean = false) {
        if (!isPolling) {
            uiState.value = IntentViewState.Loading
        }

        viewModelScope.launch {
           val result = authApiService.checkRequestID(reqId)
            if(result.isSuccessful){
                if (!isIntentDone(result.body()!!)) {
                    uiState.value = null
                } else {
                    uiState.value = IntentViewState.Done
                }
            }else{
                uiState.value = IntentViewState.Error(UtilCommon.parseErrorMessage(result.errorBody()))
            }
        }
    }

    private fun isIntentDone(intentAuth: IntentAuthenticationResponseRemote): Boolean {
        val isOtpVerified = !intentAuth.requiresOTP || intentAuth.isOtpVerified
        val isPINVerified = !intentAuth.requirePIN || intentAuth.isPINVerified
        val isIvrVerified = !intentAuth.requiresIVR || intentAuth.isIvrVerified

//        return intentAuth.isIntentAuthCompleted TODO waiting for backend fix
        return (isOtpVerified && isPINVerified && isIvrVerified) || intentAuth.isIntentAuthCompleted
    }

//    private fun nextStep(data: IntentAuth) {
//        if ((!data.isOtpVerified && data.requiresOTP)) {
//            screenType.value = IntentWidgetType.OTP
//        } else if ((!data.isPINVerified && data.requirePIN)) {
//            screenType.value = IntentWidgetType.PIN
//        } else if ((!data.isIvrVerified && data.requiresIVR)) {
//            screenType.value = IntentWidgetType.IVR
//        }
//    }

    fun clearState() {//TODO SharedFlow
        uiState.value = null
    }
}