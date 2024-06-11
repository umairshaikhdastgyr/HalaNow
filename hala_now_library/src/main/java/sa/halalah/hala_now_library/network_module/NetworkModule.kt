package sa.halalah.hala_now_library.network_module

//import com.chuckerteam.chucker.api.ChuckerCollector
//import com.chuckerteam.chucker.api.ChuckerInterceptor
//import com.chuckerteam.chucker.api.RetentionManager

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sa.halalah.hala_now_library.HalaNowApplication
import sa.halalah.hala_now_library.core_models.UserDataHolder
import sa.halalah.hala_now_library.pay_later.repository.PayLaterAPIs


object NetworkModule {

    private const val BASE_URL = "https://dummy.restapiexample.com/"

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                addInterceptor(
                    Interceptor { chain ->
                        val builder = chain.request().newBuilder()
                        builder.header("Authorization", UserDataHolder.getUserData().token)
                        builder.header("Content-Type", "application/json")
                        builder.header("package", "sa.halalah.business")
                        builder.header("Ip-Address", "103.86.53.11")
                        builder.header("AndroidBuildNumber", "200344")
                        builder.header("AndroidAppVersion", "7.0.13-staging")
                        builder.header(
                            "User-Agent",
                            "Hala Business/7.0.13-staging OnePlus LE2125/ddd3f86eabd4d3a3 Android 14"
                        )
                        builder.header("Accept-Language", "en")
                        return@Interceptor chain.proceed(builder.build())
                    }
                )
//                addInterceptor(
//                    Interceptor { chain ->
//                        return@Interceptor  ChuckerInterceptor(HalaNowApplication.Companion.applicationContext())
//                            .intercept(chain)
//                    }
//                )
            }.build()
    }

    private val provideRetrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.example.com/")
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun provideApiService(): PayLaterAPIs =  provideRetrofit.create(PayLaterAPIs::class.java)

}