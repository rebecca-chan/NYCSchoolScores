package com.example.nycschoolscores.fakes

import com.example.nycschoolscores.data.SATScores
import com.example.nycschoolscores.fixtures.ScoresFixtures
import com.example.nycschoolscores.scores.ScoresRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeScoresRepository : ScoresRepository {

    private lateinit var scores: Response<List<SATScores>>

    override suspend fun getScoresForSchool(id: String): Response<List<SATScores>> {
       return scores
    }

    fun setSuccessfulScoresResponse() {
        scores = Response.success(ScoresFixtures.listOfOneScore)
    }

    fun setErrorScoresResponse() {
        scores = Response.error(400, "{\"key\":[\"wrongKey\"]}"
            .toResponseBody("application/json".toMediaTypeOrNull()))
    }

}