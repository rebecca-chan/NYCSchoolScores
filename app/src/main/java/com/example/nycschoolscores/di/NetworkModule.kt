package com.example.nycschoolscores.di

import com.example.nycschoolscores.api.AuthenticationInterceptor
import com.example.nycschoolscores.api.SchoolsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://data.cityofnewyork.us/resource/"

    @Provides
    @Singleton
    fun provideOKHttpClient(interceptor: AuthenticationInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSchoolsApi(retrofit: Retrofit): SchoolsApi =
        retrofit.create(SchoolsApi::class.java)
}