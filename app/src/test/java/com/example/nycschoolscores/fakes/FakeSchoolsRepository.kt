package com.example.nycschoolscores.fakes

import com.example.nycschoolscores.data.School
import com.example.nycschoolscores.fixtures.SchoolsFixtures
import com.example.nycschoolscores.schools.SchoolsRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeSchoolsRepository : SchoolsRepository {

    private lateinit var schoolsResponse: Response<List<School>>

    override suspend fun fetchSchools(): Response<List<School>> {
        return schoolsResponse
    }

    fun setSuccessfulSchoolResponse() {
        schoolsResponse = Response.success(SchoolsFixtures.schools)
    }

    fun setErrorSchoolResponse() {
        schoolsResponse = Response.error(400, "{\"key\":[\"wrongKey\"]}"
            .toResponseBody("application/json".toMediaTypeOrNull()))
    }
}