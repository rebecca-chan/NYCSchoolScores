package com.example.nycschoolscores.fakes

import android.util.Log
import com.example.nycschoolscores.data.SATScores
import com.example.nycschoolscores.fixtures.SchoolsFixtures
import com.example.nycschoolscores.fixtures.ScoresFixtures
import com.example.nycschoolscores.scores.ScoresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeScoresRepository : ScoresRepository {

    var allScores: SATScores? = null

    override suspend fun fetchAllScores() {
        // do nothing
    }

    override suspend fun getScoresForSchool(id: String): SATScores? {
        return allScores
    }

    fun setScores() {
        allScores = ScoresFixtures.SCORES_1
    }


}