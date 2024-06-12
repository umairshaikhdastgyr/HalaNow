package sa.halalah.hala_now_library.authentication.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.Path
import sa.halalah.business.ivr.data.models.request.RetryOTPRequestRemote
import sa.halalah.business.ivr.data.models.request.VerifyOTPRequestRemote
import sa.halalah.hala_now_library.authentication.models.IntentAuthenticationResponseRemote

interface AuthAPIs {

    @HTTP(
        method = "POST",
        path = "https://api-stg.hala.com/user/intents/verify-otp",
        hasBody = true,
    )
    suspend fun verifyOTP(@Body verifyOTPRequest: VerifyOTPRequestRemote): Response<Unit>


    @HTTP(
        method = "POST",
        path = "https://api-stg.hala.com/user/intents/retry-otp",
        hasBody = true,
    )
    suspend fun retryOTP(@Body retryOTPRequest: RetryOTPRequestRemote): Response<Unit>


    @HTTP(
        path ="https://api-stg.hala.com/user/intents/{reqID}",
        method = "GET",
        hasBody = false
    )
    suspend fun checkRequestID(@Path("reqID") req: String): Response<IntentAuthenticationResponseRemote>
}