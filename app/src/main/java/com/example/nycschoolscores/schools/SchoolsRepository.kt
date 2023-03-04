package com.example.nycschoolscores.schools

import com.example.nycschoolscores.data.School
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SchoolsRepository {

    fun getSchools() : Flow<List<School>>

    suspend fun fetchSchools()

}