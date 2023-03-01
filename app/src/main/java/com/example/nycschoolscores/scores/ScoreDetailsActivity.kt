package com.example.nycschoolscores.scores

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.nycschoolscores.R
import com.example.nycschoolscores.databinding.ActivitySchoolScoreDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Displays SAT Scores for selected School
 */
@AndroidEntryPoint
class ScoreDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySchoolScoreDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: ScoresViewModel by viewModels()

        viewModel.scores.observe(this) {
            run {
                binding.apply {
                    schoolName.text = it?.let { it.name}
                    mathScoreAvg.text = getString(R.string.math_score, it.math)
                    readingScoreAvg.text = getString(R.string.reading_score, it.reading)
                    writingScoreAvg.text = getString(R.string.writing_score, it.writing)
                }
            }
        }
        viewModel.loadingState.observe(this) { loadingState ->
            binding.progressCircular.isVisible = loadingState
        }
        viewModel.errorState.observe(this) { error ->
            binding.error.isVisible = true
        }

        val id = intent.getStringExtra("id")
        if (id != null) {
            viewModel.fetchScores(id)
        }
    }
}