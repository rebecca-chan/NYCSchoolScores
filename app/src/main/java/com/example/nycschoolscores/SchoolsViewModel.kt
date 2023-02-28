package com.example.nycschoolscores

import android.util.Log
import androidx.lifecycle.*
import com.example.nycschoolscores.api.SchoolsService
import com.example.nycschoolscores.data.School
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class SchoolsViewModel : ViewModel() {

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

    fun getSchools() {
        _loadingState.value = true
        viewModelScope.launch {
            val response = try {
                SchoolsService.api.getSchools()
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
            } catch (throwable: Throwable) {
               _loadingState.value = false
                _errorState.value = throwable
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val schools = response.body()!!
                _schools.value = schools
                _loadingState.value = false
            }
        }
    }
}