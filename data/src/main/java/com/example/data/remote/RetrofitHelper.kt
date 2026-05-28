package com.example.data.remote

import com.example.data.network.AuthEventBus
import com.example.data.network.AuthEvent
import com.example.data.remote.api.AuthAPI
import com.example.data.remote.api.DonationAPI
import com.example.data.remote.api.DonorAPI
import com.example.data.remote.api.ScannerApi
import com.example.data.remote.api.UserAPI
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy

object RetrofitHelper {
    private const val DONOR_URL = "https://api2.donorsearch.org/api/"
    val AUTH_URL = "http://192.168.0.116:8080/api/v1/"


    private val cookieManager = CookieManager().apply {
        setCookiePolicy(CookiePolicy.ACCEPT_ALL)
    }


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }


    private val authInterceptor = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)


        if (response.code == 401) {
            AuthEventBus.emit(AuthEvent.UNAUTHORIZED)
        }

        response
    }


    private val authClient = OkHttpClient.Builder()
        .cookieJar(JavaNetCookieJar(cookieManager))
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()


    private val authRetrofit = Retrofit.Builder()
        .baseUrl(AUTH_URL)
        .client(authClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()




    val donorAPI: DonorAPI by lazy {
        Retrofit.Builder()
            .baseUrl(DONOR_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DonorAPI::class.java)
    }


    val authAPI: AuthAPI by lazy { authRetrofit.create(AuthAPI::class.java) }
    val donationAPI: DonationAPI by lazy { authRetrofit.create(DonationAPI::class.java) }
    val userAPI: UserAPI by lazy { authRetrofit.create(UserAPI::class.java) }
    val scannerAPI: ScannerApi by lazy { authRetrofit.create(ScannerApi::class.java) }
}