package com.example.nycschoolscores.schools

import com.example.nycschoolscores.api.SchoolsApi
import com.example.nycschoolscores.data.School
import retrofit2.Response
import javax.inject.Inject

/**
 * Implementation class for [SchoolsRepository]
 */
class SchoolsRepositoryImpl @Inject constructor(
    private val schoolsApi: SchoolsApi
) : SchoolsRepository {

    override suspend fun getSchools(): Response<List<School>> {
       return schoolsApi.getSchools()
    }
}