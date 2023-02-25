package com.interview.princethreatre.data

data class FilmResponse(val Provider: String, val Movies: List<FilmDetail>)

data class FilmDetail(
    val ID: String,
    val Title: String,
    val Type: String,
    val Poster: String,
    val Actors: String,
    val Price: Double
)