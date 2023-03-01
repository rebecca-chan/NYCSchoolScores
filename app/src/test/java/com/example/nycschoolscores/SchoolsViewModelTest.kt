package com.example.nycschoolscores

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nycschoolscores.fakes.FakeSchoolsRepository
import com.example.nycschoolscores.fixtures.SchoolsFixtures
import com.example.nycschoolscores.schools.SchoolsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SchoolsViewModelTest {

    private val fakeSchoolsRepository = FakeSchoolsRepository()
    private lateinit var schoolsViewModel: SchoolsViewModel

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

    @Test
    fun `should return list of schools on success` () {
        fakeSchoolsRepository.setSuccessfulSchoolResponse()
        schoolsViewModel = SchoolsViewModel(fakeSchoolsRepository)

        assertThat(schoolsViewModel.schools.value).isEqualTo(SchoolsFixtures.schools)
        assertThat(schoolsViewModel.errorState.value).isNull()
    }

    @Test
    fun `should return error when response is error` () {
        fakeSchoolsRepository.setErrorSchoolResponse()
        schoolsViewModel = SchoolsViewModel(fakeSchoolsRepository)

        assertThat(schoolsViewModel.errorState.value).isExactlyInstanceOf(Throwable::class.java)
        assertThat(schoolsViewModel.schools.value).isNull()
    }

}