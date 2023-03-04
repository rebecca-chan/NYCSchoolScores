package com.example.nycschoolscores.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nycschoolscores.db.SchoolsDao
import com.example.nycschoolscores.db.SchoolsDatabase
import com.example.nycschoolscores.db.ScoresDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideSchoolsDao(schoolsDatabase: SchoolsDatabase) : SchoolsDao {
        return schoolsDatabase.schoolsDao()
    }

    @Provides
    fun provideScoresDao(schoolsDatabase: SchoolsDatabase) : ScoresDao {
        return schoolsDatabase.scoresDao()
    }

    @Provides
    @Singleton
    fun provideSchoolsDatabase(@ApplicationContext context: Context) : SchoolsDatabase{
        return Room.databaseBuilder(context,
        SchoolsDatabase::class.java, "schools-db").build()
    }


}