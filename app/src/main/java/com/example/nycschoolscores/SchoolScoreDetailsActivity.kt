package com.example.nycschoolscores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.nycschoolscores.databinding.ActivitySchoolScoreDetailsBinding

class SchoolScoreDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySchoolScoreDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: SchoolScoresViewModel by viewModels()

        viewModel.scores.observe(this) {
            run {
                binding.apply {
                    schoolName.text = it.name
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