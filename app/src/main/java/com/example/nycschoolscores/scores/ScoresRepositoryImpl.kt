package com.example.nycschoolscores.scores

import com.example.nycschoolscores.api.SchoolsApi
import com.example.nycschoolscores.data.SATScores
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementation class for [ScoresRepository]
 */
class ScoresRepositoryImpl @Inject constructor(private val schoolsApi: SchoolsApi) :
    ScoresRepository {

    override suspend fun getScores(id: String): Response<List<SATScores>> {
        return schoolsApi.getScores(id)
    }
}