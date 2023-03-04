package com.example.nycschoolscores.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nycschoolscores.data.SATScores
import com.example.nycschoolscores.data.School

@Database(entities = [School::class, SATScores::class], version = 1)
abstract class SchoolsDatabase : RoomDatabase() {

    abstract fun schoolsDao(): SchoolsDao

    abstract fun scoresDao(): ScoresDao

}
