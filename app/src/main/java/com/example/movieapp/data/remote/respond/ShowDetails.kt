package com.example.movieapp.data.remote.respond

data class ShowDetails(
    val title: String,
    val overview: String,
    val releaseYear: Int,
    val genres: String,
    val directors: String,
    val cast: String,
    val rating: Int,
    val runtime: Int,
    val posterUrl: String
)