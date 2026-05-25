package com.example.data.remote

import com.example.data.remote.api.AuthAPI
import com.example.data.remote.api.DonationAPI
import com.example.data.remote.api.DonorAPI
import com.example.data.remote.api.UserAPI
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor
import okhttp3.Cookie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object RetrofitHelper {
    private const val DONOR_URL = "https://api2.donorsearch.org/api/"
    private const val AUTH_URL = "http://10.0.2.2:8080/"



    private val cookieJar = PersistentCookieJar(
        SetCookieCache(),
        object : CookiePersistor {
            private val cookies = mutableListOf<Cookie>()
            override fun saveAll(cookies: Collection<Cookie>) { this.cookies.addAll(cookies) }
            override fun loadAll(): List<Cookie> = cookies
            override fun removeAll(cookies: Collection<Cookie>) { this.cookies.removeAll(cookies) }
            override fun clear() { this.cookies.clear() }
        }
    )
    private val authClient = okhttp3.OkHttpClient.Builder()
        .cookieJar(cookieJar)
        .build()


    val donorAPI: DonorAPI by lazy {
        Retrofit.Builder()
            .baseUrl(DONOR_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DonorAPI::class.java)
    }

    val authAPI: AuthAPI by lazy { createAuthApi() }
    val donationAPI: DonationAPI by lazy { createDonationApi() }
    val userAPI: UserAPI by lazy { createUserApi() }

    private fun createAuthApi() = Retrofit.Builder()
        .baseUrl(AUTH_URL)
        .client(authClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthAPI::class.java)

    private fun createDonationApi() = Retrofit.Builder()
        .baseUrl(AUTH_URL)
        .client(authClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DonationAPI::class.java)

    private fun createUserApi() = Retrofit.Builder()
        .baseUrl(AUTH_URL)
        .client(authClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserAPI::class.java)
}