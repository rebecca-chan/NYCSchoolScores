package com.example.nycschoolscores.scores

import com.example.nycschoolscores.data.SATScores

/**
 * Repository class to fetch scores data
 */
interface ScoresRepository {

    suspend fun fetchAllScores()

    suspend fun getScoresForSchool(id: String) : SATScores?

}