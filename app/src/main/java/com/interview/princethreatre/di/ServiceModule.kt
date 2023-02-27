package com.interview.princethreatre.di

import com.interview.princethreatre.service.FilmService
import com.interview.princethreatre.service.FilmServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun bindFilmService(filmService: FilmServiceImpl): FilmService
}