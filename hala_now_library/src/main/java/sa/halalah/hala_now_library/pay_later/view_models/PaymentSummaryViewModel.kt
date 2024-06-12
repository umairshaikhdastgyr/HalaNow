package sa.halalah.hala_now_library.pay_later.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import sa.halalah.hala_now_library.authentication.models.IntentAuthenticationResponseRemote
import sa.halalah.hala_now_library.pay_later.models.ConfirmPaylaterRequest
import sa.halalah.hala_now_library.pay_later.models.ConfirmationPayLaterViewState
import sa.halalah.hala_now_library.pay_later.repository.PayLaterRepository
import sa.halalah.hala_now_library.utils.UtilCommon

class PaymentSummaryViewModel() : ViewModel() {

    private val payLaterAPIs = PayLaterRepository.payLateApiService()
    private val _confirmPayLaterRes = MutableStateFlow<ConfirmationPayLaterViewState?>(null)
    val confirmPayLaterRes: StateFlow<ConfirmationPayLaterViewState?> = _confirmPayLaterRes


    fun confirmPayLater(
        payload: ConfirmPaylaterRequest
    ) {
        viewModelScope.launch {
            _confirmPayLaterRes.value = ConfirmationPayLaterViewState.Loading
            val result = payLaterAPIs.confirmPayLaterOrder(payload)
            if (result.isSuccessful) {
                _confirmPayLaterRes.value = ConfirmationPayLaterViewState.Data(result.body()!!)
            } else if (result.code() == 799) {
                result.errorBody()?.let {
                    val intentResponseRemote =
                        Gson().fromJson(it.string(), IntentAuthenticationResponseRemote::class.java)
                    _confirmPayLaterRes.value =
                        ConfirmationPayLaterViewState.IntentAuthRequired(intentResponseRemote)
                }
            } else {
                _confirmPayLaterRes.value =
                    ConfirmationPayLaterViewState.Error(UtilCommon.parseErrorMessage(result.errorBody()))
            }
        }
    }


//    private fun handleException(throwable: Throwable) {
//        if (throwable is ApiException && throwable.data != null && throwable.responseCode == 799) {
//            _confirmPayLaterRes.value = ConfirmationPayLaterViewState.IntentAuthRequired(throwable.data as IntentAuthenticationResponseRemote)
//        } else {
//            _confirmPayLaterRes.value = ConfirmationPayLaterViewState.Error(throwable.message ?: "")
//        }
//    }

    fun resetConfirmPayLateState() {
        _confirmPayLaterRes.value = null
    }


}