package sa.halalah.hala_now_library.pay_later.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import sa.halalah.hala_now_library.authentication.repository.AuthAPIs
import sa.halalah.hala_now_library.network_module.NetworkModule
import sa.halalah.hala_now_library.pay_later.models.PayLaterOrderRequest

object PayLaterRepository {
    fun payLateApiService(): PayLaterAPIs =  NetworkModule.provideRetrofit.create(PayLaterAPIs::class.java)
}