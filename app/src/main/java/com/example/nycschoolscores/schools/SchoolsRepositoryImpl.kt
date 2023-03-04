package com.example.nycschoolscores.schools

import androidx.annotation.WorkerThread
import com.example.nycschoolscores.api.SchoolsApi
import com.example.nycschoolscores.data.School
import com.example.nycschoolscores.db.SchoolsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementation class for [SchoolsRepository]
 */
class SchoolsRepositoryImpl @Inject constructor(
    private val schoolsApi: SchoolsApi,
    private val schoolsDao: SchoolsDao
) : SchoolsRepository {

    override fun getSchools(): Flow<List<School>> = schoolsDao.getAllSchools()

    // fetches schools from network and inserts them into db
    override suspend fun fetchSchools() {
        withContext(Dispatchers.IO) {
            val response = schoolsApi.getSchools()
            if (response.isSuccessful && response.body() != null) {
                val schools = response.body()!!
                schoolsDao.insertAll(schools)
            }
        }
    }

}