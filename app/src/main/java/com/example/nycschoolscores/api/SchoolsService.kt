package com.example.nycschoolscores.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SchoolsService {

    private const val BASE_URL = "https://data.cityofnewyork.us/resource/"

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthenticationInterceptor()).build()

    val api: SchoolsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(SchoolsApi::class.java)
    }
}

class AuthenticationInterceptor : Interceptor {

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