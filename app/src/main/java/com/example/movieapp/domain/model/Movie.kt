package com.example.movieapp.domain.model

import com.example.movieapp.data.remote.respond.Genre
import com.example.movieapp.data.remote.respond.ImageSet

data class Movie(
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
    val directors: List<String>,
    val cast: List<String>,
    val rating: Int,
    val runtime: Int,
    val imageSet: ImageSet
)