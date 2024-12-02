package com.example.movieapp.data.local.movie

import androidx.room.PrimaryKey
import com.example.movieapp.data.remote.respond.Genre
import com.example.movieapp.data.remote.respond.ImageSet

data class MovieEntity(
    val cast: String,
    val directors: String,
    val genres: String,
    @PrimaryKey
    val id: String,
    val imageSet: ImageSet,
    val imdbId: String,
    val itemType: String,
    val originalTitle: String,
    val overview: String,
    val rating: Int,
    val releaseYear: Int,
    val runtime: Int,
    val showType: String,
    val title: String,
    val tmdbId: String,
    val category: String
)
