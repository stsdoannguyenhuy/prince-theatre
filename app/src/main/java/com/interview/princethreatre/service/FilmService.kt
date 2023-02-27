package com.interview.princethreatre.service

import com.interview.princethreatre.data.FilmDetail
import com.interview.princethreatre.data.FilmProvider
import com.interview.princethreatre.data.FilmResponse
import com.interview.princethreatre.repository.FilmRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

interface FilmService {
    fun getFilmWorldFilms(): Observable<FilmResponse>
    fun getCinemaWorldFilms(): Observable<FilmResponse>
    fun sortByPrice(listFilm: MutableList<FilmDetail>);
}

/**
 * Your business logic should be handle here!
 * */
@Singleton
class FilmServiceImpl @Inject constructor(private val filmRepository: FilmRepository) :
    FilmService {


    override fun getFilmWorldFilms(): Observable<FilmResponse> {
        return filmRepository.getFilms(FilmProvider.FILM_WORLD.apiName)
    }

    override fun getCinemaWorldFilms(): Observable<FilmResponse> {
        return filmRepository.getFilms(FilmProvider.CINEMA_WORLD.apiName)
    }

    override fun sortByPrice(listFilm: MutableList<FilmDetail>) {
        val lengthComparator =
            Comparator { film1: FilmDetail, film2: FilmDetail -> if (film1.price > film2.price) return@Comparator 1 else return@Comparator -1 }
        listFilm.sortWith(lengthComparator)
    }


}