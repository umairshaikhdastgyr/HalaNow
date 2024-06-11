package sa.halalah.hala_now_library.pay_later.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import sa.halalah.hala_now_library.network_module.NetworkModule
import sa.halalah.hala_now_library.pay_later.models.PayLaterOrderRequest

class PayLaterRepository {

    private val payLaterAPIs = NetworkModule.provideApiService()

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