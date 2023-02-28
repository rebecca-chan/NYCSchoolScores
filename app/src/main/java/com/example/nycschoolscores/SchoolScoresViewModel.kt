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
    val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState
    val _errorState = MutableLiveData<Throwable>()
    val errorState: LiveData<Throwable>
        get() = _errorState

    fun fetchScores(schoolId: String) {
        _loadingState.value = true
        viewModelScope.launch {
            val response = try {
                SchoolsService.api.getScores(schoolId)
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
            } catch (throwable: Throwable) {
                _loadingState.value = false
                _errorState.value = throwable
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                _scores.value = response.body()?.getOrNull(0)
                _loadingState.value = false
            }
        }
    }
}