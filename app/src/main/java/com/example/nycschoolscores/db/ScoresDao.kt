package com.example.nycschoolscores.db

import androidx.room.Dao
import androidx.room.Query
import com.example.nycschoolscores.data.SATScores

@Dao
interface ScoresDao {

    @Query("SELECT * FROM scores")
    fun getAllScores() : List<SATScores>

}