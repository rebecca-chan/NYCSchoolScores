package com.example.nycschoolscores

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nycschoolscores.fakes.FakeScoresRepository
import com.example.nycschoolscores.fixtures.ScoresFixtures
import com.example.nycschoolscores.fixtures.ScoresFixtures.Companion.SCORES_1
import com.example.nycschoolscores.scores.ScoresViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ScoresViewModelTest {
    private val fakeScoresRepository = FakeScoresRepository()
    private lateinit var scoresViewModel: ScoresViewModel

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
    fun `should show scores on response success` () {
        fakeScoresRepository.setScores()
        scoresViewModel = ScoresViewModel(fakeScoresRepository)
        scoresViewModel.fetchScores(SCORES_1.id)

        Assertions.assertThat(scoresViewModel.scores.value).isEqualTo(SCORES_1)
        Assertions.assertThat(scoresViewModel.errorState.value).isNull()
    }

}