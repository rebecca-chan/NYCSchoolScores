package com.example.nycschoolscores.schools

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschoolscores.data.School
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Viewmodel for [MainActivity]
 */
@HiltViewModel
class SchoolsViewModel @Inject constructor(private val schoolsRepository: SchoolsRepository) :
    ViewModel() {

    private val TAG = this.javaClass.name

    val _schools = MutableLiveData<List<School>>()
    val schools: LiveData<List<School>>
        get() = _schools
    val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState
    val _errorState = MutableLiveData<Throwable>()
    val errorState: LiveData<Throwable>
        get() = _errorState


    init {
        getSchools()
    }

    // TODO: set up wrapper for Loading, Content, Error states


    private fun getSchools() {
        _loadingState.value = true
        viewModelScope.launch {
            val response = try {
                schoolsRepository.getSchools()
            } catch (e: IOException) {
                Log.e(TAG, "IO Exception")
                _loadingState.value = false
                _errorState.value = e
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HTTP Exception")
                _loadingState.value = false
                _errorState.value = e
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val schools = response.body()!!
                _schools.value = schools
            } else {
                _errorState.value = Throwable()
            }
            _loadingState.value = false
        }
    }
}