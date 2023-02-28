package com.example.nycschoolscores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nycschoolscores.api.SchoolsService
import com.example.nycschoolscores.databinding.ActivityMainBinding
import com.example.nycschoolscores.ui.SchoolAdapter
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val TAG = "Main activity"

    private lateinit var binding: ActivityMainBinding
    val schoolAdapter = SchoolAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            schoolRecyclerView.adapter = schoolAdapter
            schoolRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launch {
            val response = try {
                SchoolsService.api.getSchools()
            } catch (e: IOException) {
                Log.e(TAG, "IO Exception")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HTTP Exception")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val schools = response.body()!!
                schoolAdapter.submitList(schools)
            }
        }
    }
}