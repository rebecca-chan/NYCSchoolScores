package com.example.nycschoolscores.schools

import com.example.nycschoolscores.data.School
import retrofit2.Response

interface SchoolsRepository {

    suspend fun getSchools() : Response<List<School>>

}