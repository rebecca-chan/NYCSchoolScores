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

    init {
        getSchools()
    }

    fun getSchools() {
        viewModelScope.launch {
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
                _schools.value = schools
            }
        }
    }
}