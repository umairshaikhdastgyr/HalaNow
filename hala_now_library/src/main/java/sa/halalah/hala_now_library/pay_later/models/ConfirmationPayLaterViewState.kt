package sa.halalah.hala_now_library.pay_later.models

import sa.halalah.hala_now_library.authentication.models.IntentAuthenticationResponseRemote

sealed class ConfirmationPayLaterViewState {
    data object Loading : ConfirmationPayLaterViewState()
    data class Data(val response: ConfirmPayLaterOrderResponse) : ConfirmationPayLaterViewState()
    data class IntentAuthRequired(val response: IntentAuthenticationResponseRemote) : ConfirmationPayLaterViewState()
    data class Error(val message: String) : ConfirmationPayLaterViewState()
}