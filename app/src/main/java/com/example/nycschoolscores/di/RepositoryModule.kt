package com.example.nycschoolscores.di

import com.example.nycschoolscores.schools.SchoolsRepository
import com.example.nycschoolscores.schools.SchoolsRepositoryImpl
import com.example.nycschoolscores.scores.ScoresRepository
import com.example.nycschoolscores.scores.ScoresRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindScoresRepository(impl: ScoresRepositoryImpl): ScoresRepository

    @Singleton
    @Binds
    abstract fun bindSchoolsRepository(impl: SchoolsRepositoryImpl): SchoolsRepository
}