package com.example.nycschoolscores.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nycschoolscores.data.SATScores
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoresDao {

    @Query("SELECT * FROM scores")
    fun getAllScores() : List<SATScores>

    @Query("SELECT * FROM scores WHERE dbn = :id LIMIT 1")
    fun getScoresForSchool(id: String): SATScores?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sores: List<SATScores>)

}