package com.example.nycschoolscores.api

import com.example.nycschoolscores.data.SATScores
import com.example.nycschoolscores.data.School
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolsApi {

    @GET("s3k6-pzi2.json")
    suspend fun getSchools(
    ): Response<List<School>>

    @GET("f9bf-2cp4.json")
    suspend fun getScores(
        @Query("dbn") dbn: String
    ): Response<List<SATScores>>

}