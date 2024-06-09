package sa.halalah.hala_now_library.pay_later.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.Query
import sa.halalah.hala_now_library.pay_later.models.CreatePaylaterOrderResponse
import sa.halalah.hala_now_library.pay_later.models.PayLaterOrderRequest

interface PayLaterAPIs {

    @HTTP(
        method = "POST",
        path = "https://api-stg.hala.com/pay-later-orders/api/v1/PayLaterOrder",
        hasBody = true
    )
    suspend fun submitPayLaterOrder(
        @Body request: PayLaterOrderRequest,
        @Query("entityId") entityId: String = "",
        @Query("language") language: String? = "en"
    ): Response<CreatePaylaterOrderResponse>
}