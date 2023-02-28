package com.example.nycschoolscores

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschoolscores.api.SchoolsService
import com.example.nycschoolscores.data.SATScores
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class SchoolScoresViewModel: ViewModel() {

    private val TAG = this.javaClass.name

    val _scores = MutableLiveData<SATScores>()
    val scores: LiveData<SATScores>
        get() = _scores

    fun fetchScores(schoolId: String) {
        viewModelScope.launch {
            val response = try {
                SchoolsService.api.getScores(schoolId)
            } catch (e: IOException) {
                Log.e(TAG, "io exception")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "http exception")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                _scores.value = response.body()?.getOrNull(0)
            }
        }
    }
}