package com.interview.princethreatre.data

import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("Provider") val provider: String, @SerializedName("Movies") val movies: MutableList<FilmDetail>)

data class FilmDetail(
    @SerializedName("ID") val id: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Actors") val actors: String,
    @SerializedName("Price") val price: Double
)

enum class FilmProvider(val apiName: String, val showName: String){
    FILM_WORLD("filmworld","Film World"),
    CINEMA_WORLD("cinemaworld","Cinema World"),
}