package com.interview.princethreatre.di

import com.interview.princethreatre.repository.DataSource
import com.interview.princethreatre.repository.FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideDataSource(): DataSource {
        return DataSource()
    }

    @Provides
    @Singleton
    fun provideFilmRepo(dataSource: DataSource): FilmRepository {
        return dataSource.filmRepository
    }
}