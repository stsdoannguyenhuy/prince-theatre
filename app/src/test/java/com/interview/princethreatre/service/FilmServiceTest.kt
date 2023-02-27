package com.interview.princethreatre.service

import android.annotation.SuppressLint
import com.interview.princethreatre.data.FilmDetail
import com.interview.princethreatre.data.FilmProvider
import com.interview.princethreatre.data.FilmResponse
import com.interview.princethreatre.di.NetworkModule
import com.interview.princethreatre.repository.FilmRepository
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FilmServiceTest {
    private val filmService: FilmService
    private val filmRepo: FilmRepository

    init {
        val networkModule = NetworkModule()
        val dataSource = networkModule.provideDataSource()
        filmRepo = networkModule.provideFilmRepo(dataSource)
        filmService = FilmServiceImpl(filmRepo)
    }


    @Test
    fun testGetFilmWorld() {
        val filmResponse = filmService.getFilmWorldFilms()
            .blockingFirst()
        Assert.assertEquals(FilmProvider.FILM_WORLD.showName, filmResponse.provider)
        Assert.assertNotNull(filmResponse.movies)
    }

    @Test
    fun testGetCinemaWorld() {
        val filmResponse = filmService.getCinemaWorldFilms()
            .blockingFirst()
        Assert.assertEquals(FilmProvider.CINEMA_WORLD.showName, filmResponse.provider)
        Assert.assertNotNull(filmResponse.movies)
    }


    @Test
    fun testGetFilmMockData() {
        val mockFilmRepo = mock<FilmRepository> {
            on{getFilms(MockitoHelper.anyObject())} doReturn Observable.just(
                FilmResponse(
                    FilmProvider.CINEMA_WORLD.showName,
                    listOf(
                        FilmDetail(
                            "any",
                            "any",
                            "any",
                            "any",
                            "any",
                            Double.MIN_VALUE
                        )
                    ).toMutableList()
                ))
        }
        val filmService = FilmServiceImpl(mockFilmRepo)
        val filmResponse = filmService.getCinemaWorldFilms()
            .blockingFirst()
        Assert.assertNotNull(filmResponse.provider)
        Assert.assertNotNull(filmResponse.movies)
        Assert.assertNotNull(filmResponse.movies[0])
        Assert.assertNotNull(filmResponse.movies[0].id)
        Assert.assertNotNull(filmResponse.movies[0].title)
        Assert.assertNotNull(filmResponse.movies[0].type)
        Assert.assertNotNull(filmResponse.movies[0].actors)
        Assert.assertNotNull(filmResponse.movies[0].poster)
        Assert.assertNotNull(filmResponse.movies[0].price)
    }
}

//Kotlin Mockito problem: https://stackoverflow.com/questions/59230041/argumentmatchers-any-must-not-be-null
object MockitoHelper {
    @SuppressLint("CheckResult")
    fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    @Suppress("UNCHECKED_CAST")
    fun <T> uninitialized(): T = null as T
}