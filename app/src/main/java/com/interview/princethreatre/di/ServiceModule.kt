package com.interview.princethreatre.di

import com.interview.princethreatre.ui.service.FilmService
import com.interview.princethreatre.ui.service.FilmServiceImpl
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