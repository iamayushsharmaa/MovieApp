package com.example.movieapp.data.local.movie.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.data.remote.respond.Genre
import com.example.movieapp.data.remote.respond.ImageSet

@Entity
data class MovieEntity(
    val itemType: String,
    val showType: String,
    @PrimaryKey
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
