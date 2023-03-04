package com.example.nycschoolscores.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nycschoolscores.data.School
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolsDao {

    @Query("SELECT * FROM schools")
    fun getAllSchools() : Flow<List<School>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(schools: List<School>)

    @Query("DELETE FROM schools")
    suspend fun deleteAll()

}