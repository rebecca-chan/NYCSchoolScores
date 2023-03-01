package com.example.nycschoolscores.schools

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nycschoolscores.databinding.ActivityMainBinding
import com.example.nycschoolscores.scores.ScoreDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Entry point for app, displays list of NYC schools and its neighborhood location
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var schoolsAdapter: SchoolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
           schoolsAdapter = SchoolAdapter(
                SchoolAdapter.OnClickListener {
                    school ->
                    intent = Intent(this@MainActivity, ScoreDetailsActivity::class.java)
                    intent.putExtra("id", school.id)
                    this@MainActivity.startActivity(intent)
                }
            )
            schoolRecyclerView.adapter = schoolsAdapter
            schoolRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val schoolsViewModel: SchoolsViewModel by viewModels()

        schoolsViewModel.schools.observe(this) { schools -> schoolsAdapter.submitList(schools) }
        schoolsViewModel.loadingState.observe(this) { loading ->
            binding.progressCircular.isVisible = loading
        }
        schoolsViewModel.errorState.observe(this) { errorState ->
            binding.error.isVisible = true
        }
    }
}