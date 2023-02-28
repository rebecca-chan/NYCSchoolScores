package com.example.nycschoolscores.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SchoolsService {

    private const val BASE_URL =  "https://data.cityofnewyork.us/resource/"

    // TODO: use okhttpclient to cache network results

    val api: SchoolsApi by lazy {
         Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build().create(SchoolsApi::class.java)
    }
}