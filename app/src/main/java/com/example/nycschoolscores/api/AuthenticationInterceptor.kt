package com.example.nycschoolscores.api

import okhttp3.Interceptor
import javax.inject.Inject

/**
 * Interceptor for OKHTTP to add app token to headers
 */
class AuthenticationInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

        val newRequest = chain.request().newBuilder()
            .addHeader("X-App-Token", APP_TOKEN)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        const val APP_TOKEN = "efusd7GOL1wTJV6y8XOORo9ow"
    }
}