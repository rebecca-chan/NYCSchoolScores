package com.example.nycschoolscores.fakes

import com.example.nycschoolscores.api.SchoolsApi
import com.example.nycschoolscores.data.SATScores
import com.example.nycschoolscores.data.School
import com.example.nycschoolscores.fixtures.SchoolsFixtures
import com.example.nycschoolscores.fixtures.ScoresFixtures
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeSchoolsApi : SchoolsApi {

    private lateinit var allSchools: Response<List<School>>
    private lateinit var allSchoolScores: Response<List<SATScores>>
    private lateinit var scores: Response<List<SATScores>>

    override suspend fun getSchools(): Response<List<School>> {
        return allSchools
    }

    override suspend fun getScores(dbn: String): Response<List<SATScores>> {
        return scores
    }

    override suspend fun getAllScores(): Response<List<SATScores>> {
        return allSchoolScores
    }

    fun setSuccessfulSchoolResponse() {
        allSchools = Response.success(SchoolsFixtures.schools)
    }

    fun setErrorSchoolResponse() {
        allSchools = Response.error(
            400, "{\"key\":[\"wrongKey\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
    }

    fun setSuccessfulScoresResponse() {
        scores = Response.success(ScoresFixtures.listOfOneScore)
    }

    fun setErrorScoresResponse() {
        scores = Response.error(
            400, "{\"key\":[\"wrongKey\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
    }

    fun setSuccessfulAllScoresResponse() {
        allSchoolScores = Response.success(ScoresFixtures.listOfManyScores)
    }

    fun setErrorAllScoresResponse() {
        allSchoolScores = Response.error(
            400, "{\"key\":[\"wrongKey\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
    }
}