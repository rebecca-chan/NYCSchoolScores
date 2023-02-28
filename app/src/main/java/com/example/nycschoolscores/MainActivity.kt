package com.example.nycschoolscores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nycschoolscores.api.SchoolsService
import com.example.nycschoolscores.databinding.ActivityMainBinding
import com.example.nycschoolscores.ui.SchoolAdapter
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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
                    intent = Intent(this@MainActivity, SchoolScoreDetailsActivity::class.java)
                    intent.putExtra("id", school.id)
                    this@MainActivity.startActivity(intent)
                }
            )
            schoolRecyclerView.adapter = schoolsAdapter
            schoolRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val schoolsViewModel: SchoolsViewModel =
            ViewModelProvider(this).get(SchoolsViewModel::class.java)


        schoolsViewModel.schools.observe(this, {schools -> schoolsAdapter.submitList(schools)})
    }
}