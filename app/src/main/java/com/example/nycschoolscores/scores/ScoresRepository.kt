package com.example.nycschoolscores.scores

import com.example.nycschoolscores.data.SATScores
import retrofit2.Response

/**
 * Repository class to fetch scores data
 */
interface ScoresRepository {

    suspend fun getScores(id: String) : Response<List<SATScores>>

}