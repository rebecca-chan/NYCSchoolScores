package com.example.nycschoolscores

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nycschoolscores.data.School
import com.example.nycschoolscores.fakes.FakeSchoolsApi
import com.example.nycschoolscores.fakes.FakeSchoolsDao
import com.example.nycschoolscores.fixtures.SchoolsFixtures
import com.example.nycschoolscores.schools.SchoolsRepositoryImpl
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SchoolsRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    val fakeSchoolsApi = FakeSchoolsApi()
    val fakeSchoolsDao = FakeSchoolsDao()

    val schoolsRepository = SchoolsRepositoryImpl(fakeSchoolsApi, fakeSchoolsDao)

    @Test
    fun `calling getSchools returns flow of schools from schools db`() = runTest {
        fakeSchoolsDao.insertAll(SchoolsFixtures.schools)
        assertEquals(schoolsRepository.getSchools().first(), SchoolsFixtures.schools)
    }

    @Test
    fun `calling fetch schools returns list of schools from api and inserts into schools db`() =
        runTest {
            fakeSchoolsApi.setSuccessfulSchoolResponse()
            schoolsRepository.fetchSchools()
            assertEquals(fakeSchoolsDao.schoolsFlow.first(), SchoolsFixtures.schools)
        }


}