package sa.halalah.hala_now_library.network_module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sa.halalah.hala_now_library.pay_later.repository.PayLaterAPIs
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
        return  OkHttpClient.Builder()
            .apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("X-App-Version", "1.23")
                    builder.header("X-Platform", "Android")
                    builder.header("X-Auth-Token", "sgsrager32524542afg3423")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
                addInterceptor(
                    Interceptor { chain ->
                        return@Interceptor  ChuckerInterceptor(appContext)
                            .intercept(chain)
                    }
                )
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PayLaterAPIs {
        return retrofit.create(PayLaterAPIs::class.java)
    }
}