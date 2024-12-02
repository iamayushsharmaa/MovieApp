package com.example.movieapp.data.remote.respond

data class MovieList(
    val cast: List<String>?,
    val directors: List<String>?,
    val genres: List<Genre>?,
    val id: String?,
    val imageSet: ImageSet?,
    val imdbId: String?,
    val itemType: String?,
    val originalTitle: String?,
    val overview: String?,
    val rating: Int?,
    val releaseYear: Int?,
    val runtime: Int?,
    val showType: String?,
    val title: String?,
    val tmdbId: String?,
    val category : String
)