package com.example.nycschoolscores.scores

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschoolscores.data.SATScores
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * ViewModel for [ScoreDetailsActivity]
 */
@HiltViewModel
class ScoresViewModel @Inject constructor(
    private val scoresRepository: ScoresRepository
): ViewModel() {

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
    // TODO: set up coroutine exception handler

    fun fetchScores(schoolId: String) {
        _loadingState.value = true
        viewModelScope.launch {
            val response = try {
                scoresRepository.getScores(schoolId)
            } catch (e: IOException) {
                Log.e(TAG, "io exception")
                _loadingState.value = false
                _errorState.value = e
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "http exception")
                _loadingState.value = false
                _errorState.value = e
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val scores = response.body()?.getOrNull(0)
                if (scores == null) {
                    _errorState.value = Throwable()
                } else {
                    _scores.value = scores
                }
            } else {
                _errorState.value = Throwable()
            }
            _loadingState.value = false
        }
    }
}