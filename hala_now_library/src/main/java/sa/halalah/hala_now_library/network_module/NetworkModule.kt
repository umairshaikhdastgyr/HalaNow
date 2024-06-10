package sa.halalah.hala_now_library.network_module

import android.content.Context
//import com.chuckerteam.chucker.api.ChuckerCollector
//import com.chuckerteam.chucker.api.ChuckerInterceptor
//import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sa.halalah.hala_now_library.core_models.UserDataHolder
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
                    builder.header("Authorization", UserDataHolder.getUserData().token)
                    builder.header("Content-Type", "application/json")
                    builder.header("package", "sa.halalah.business")
                    builder.header("Ip-Address", "103.86.53.11")
                    builder.header("AndroidBuildNumber", "200344")
                    builder.header("AndroidAppVersion", "7.0.13-staging")
                    builder.header("User-Agent", "Hala Business/7.0.13-staging OnePlus LE2125/ddd3f86eabd4d3a3 Android 14")
                    builder.header("Accept-Language", "en")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
//                addInterceptor(
//                    Interceptor { chain ->
//                        return@Interceptor  ChuckerInterceptor(appContext)
//                            .intercept(chain)
//                    }
//                )
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