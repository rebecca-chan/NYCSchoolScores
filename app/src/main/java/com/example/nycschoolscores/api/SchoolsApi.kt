package com.example.nycschoolscores.api

import com.example.nycschoolscores.data.SATScores
import com.example.nycschoolscores.data.School
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolsApi {

    companion object {
        const val APP_TOKEN = "efusd7GOL1wTJV6y8XOORo9ow"
    }

    @GET("s3k6-pzi2.json")
    suspend fun getSchools(
        @Query("\$\$app_token") appToken: String = APP_TOKEN
    ): Response<List<School>>

    @GET("f9bf-2cp4.json")
    suspend fun getScores(
        @Query("\$\$app_token") appToken: String = APP_TOKEN,
        @Query("dbn") dbn: String
    ): Response<SATScores>

}