package com.interview.princethreatre.repository

import com.interview.princethreatre.data.FilmResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FilmRepository {
    @GET("api/v2/{theatre}/movies")
    fun getFilms(@Path("theatre") theatre: String) : Observable<FilmResponse>
}