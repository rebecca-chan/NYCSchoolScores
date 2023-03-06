package com.example.nycschoolscores.fakes

import com.example.nycschoolscores.data.School
import com.example.nycschoolscores.fixtures.SchoolsFixtures
import com.example.nycschoolscores.schools.SchoolsRepository
import io.reactivex.internal.operators.flowable.FlowableError
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class FakeSchoolsRepository : SchoolsRepository {

    var schoolsFlow: Flow<List<School>> = flowOf(listOf())

    override fun getSchools(): Flow<List<School>> {
        return flowOf(SchoolsFixtures.schools)
    }

    override suspend fun fetchSchools() {
        // do nothing
    }

    fun setSchools() {
        schoolsFlow = flowOf(SchoolsFixtures.schools)
    }


}