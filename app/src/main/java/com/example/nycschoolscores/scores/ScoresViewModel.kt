package com.example.nycschoolscores.scores

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschoolscores.data.SATScores
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

/**
 * ViewModel for [ScoreDetailsActivity]
 */
@HiltViewModel
class ScoresViewModel @Inject constructor(
    private val scoresRepository: ScoresRepository
) : ViewModel() {

    private val TAG = this.javaClass.name

    val _scores = MutableLiveData<SATScores>()
    val scores: LiveData<SATScores>
        get() = _scores
    val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState
    val _errorState = MutableLiveData<Throwable>()
    val errorState: LiveData<Throwable>
        get() = _errorState

    // TODO: set up wrapper for Loading, Content, Error states

    fun fetchScores(schoolId: String) {
        _loadingState.value = true
        viewModelScope.launch (getCoroutineExceptionHandler()){
            withContext(Dispatchers.Main) {
                val response = try {
                    scoresRepository.getScoresForSchool(schoolId)
                } catch (e: IOException) {
                    Log.e(TAG, "io exception")
                    _loadingState.value = false
                    _errorState.value = e
                    return@withContext
                }
                // if exists in db, show from db, else retry fetch
                if (response != null) {
                    _scores.value = response
                } else {
                    withContext(Dispatchers.IO) {
                        scoresRepository.fetchAllScores()
                        scoresRepository.getScoresForSchool(schoolId)
                    }
                }
                _loadingState.value = false
            }
        }
    }

    private fun getCoroutineExceptionHandler() : CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            _errorState.value = throwable
            println(throwable.printStackTrace())
        }
    }
}