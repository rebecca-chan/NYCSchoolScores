package com.example.nycschoolscores.scores

import android.util.Log
import com.example.nycschoolscores.api.SchoolsApi
import com.example.nycschoolscores.data.SATScores
import com.example.nycschoolscores.db.ScoresDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementation class for [ScoresRepository]
 */
class ScoresRepositoryImpl @Inject constructor(
    private val schoolsApi: SchoolsApi,
    val scoresDao: ScoresDao
) :
    ScoresRepository {

    val TAG = this.javaClass.name

    override suspend fun fetchAllScores() {
        withContext(Dispatchers.IO) {
            val response = try {
                schoolsApi.getAllScores()
            } catch (e: HttpException) {
                Log.e(TAG, "HTTP Exception")
                return@withContext
            }
            if (response.isSuccessful && response.body() != null) {
                val scores = response.body()!!
                scoresDao.insertAll(scores)
            }
        }
    }

    override suspend fun getScoresForSchool(id: String): SATScores? =
        withContext(Dispatchers.IO) {
            return@withContext scoresDao.getScoresForSchool(id)
        }
}