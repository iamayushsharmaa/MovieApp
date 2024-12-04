package com.example.movieapp.data.remote.respond

data class MovieList(
    val itemType: String,
    val showType: String,
    val id: String,
    val imdbId: String,
    val tmdbId: String,
    val title: String,
    val overview: String,
    val releaseYear: Int,
    val originalTitle: String,
    val genres: List<Genre>,
    val directors: String,
    val cast: String,
    val rating: Int,
    val runtime: Int,
    val imageSet: ImageSet
)


