package com.example.nycschoolscores.fakes

import com.example.nycschoolscores.data.School
import com.example.nycschoolscores.db.SchoolsDao
import com.example.nycschoolscores.fixtures.SchoolsFixtures
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSchoolsDao : SchoolsDao {

    var schoolsFlow: Flow<List<School>> = flowOf(listOf())

    override fun getAllSchools(): Flow<List<School>> {
        return schoolsFlow
    }

    override fun insertAll(schools: List<School>) {
       schoolsFlow = flowOf(schools)
    }

    override suspend fun deleteAll() {
       schoolsFlow = flowOf(listOf())
    }
}