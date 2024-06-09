package sa.halalah.hala_now_library.pay_later.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import sa.halalah.hala_now_library.pay_later.models.PayLaterOrderRequest
import javax.inject.Inject

class PayLaterRepository @Inject constructor(private val payLaterAPIs: PayLaterAPIs) {

    private val _tweets = MutableStateFlow<List<String>>(emptyList())
    val tweets: StateFlow<List<String>>
        get() = _tweets

    suspend fun getCategories() {
        val dd = PayLaterOrderRequest(
            additionalFields = hashMapOf(),
            amount = 0.0,
            attachment = "",
            notes = "",
            salesmanEntityId = "",
            requestId = "",
            supplierId = ""
        )
        val result = payLaterAPIs.submitPayLaterOrder(dd)

    }
}