package sa.halalah.hala_now_library.authentication.repository

import sa.halalah.hala_now_library.network_module.NetworkModule
import sa.halalah.hala_now_library.pay_later.models.PayLaterOrderRequest

object AuthRepo {

    val authApiService =  NetworkModule.provideRetrofit.create(AuthAPIs::class.java)
}