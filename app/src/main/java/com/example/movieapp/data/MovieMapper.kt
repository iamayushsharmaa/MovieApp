package com.example.movieapp.data

import com.example.movieapp.data.local.movie.MovieEntity
import com.example.movieapp.data.remote.respond.Genre
import com.example.movieapp.data.remote.respond.ImageSet
import com.example.movieapp.data.remote.respond.MovieList
import com.example.movieapp.domain.model.Movie
import java.util.Locale.Category


fun MovieList.toMovieEntity(
    category: String
): MovieEntity {
    return MovieEntity(
        cast = this.cast?.joinToString(", ") ?: "", // Convert list to comma-separated string
        directors = this.directors?.joinToString(", ") ?: "",
        genres = this.genres?.joinToString(", ") { it.name } ?: "", // Convert Genre list to string
        id = this.id ?: throw IllegalArgumentException("id cannot be null"),
        imageSet = this.imageSet ?: throw IllegalArgumentException("imageSet cannot be null"),
        imdbId = this.imdbId ?: "",
        itemType = this.itemType ?: "",
        originalTitle = this.originalTitle ?: "",
        overview = this.overview ?: "",
        rating = this.rating ?: 0,
        releaseYear = this.releaseYear ?: 0,
        runtime = this.runtime ?: 0,
        showType = this.showType ?: "",
        title = this.title ?: "",
        tmdbId = this.tmdbId ?: "",
        category = category
    )
}

fun MovieEntity.toMovie(
    category : String
): Movie {
    return Movie(
        cast = this.cast.split(",").map { it.trim() }, // Split by comma and trim spaces
        directors = this.directors.split(",").map { it.trim() }, // Split by comma and trim spaces
        genres = this.genres.split(",").map { (it.trim()) }, // Split and map to Genre objects
        id = this.id,
        imageSet = this.imageSet,
        imdbId = this.imdbId,
        itemType = this.itemType,
        originalTitle = this.originalTitle,
        overview = this.overview,
        rating = this.rating,
        releaseYear = this.releaseYear,
        runtime = this.runtime,
        showType = this.showType,
        title = this.title,
        tmdbId = this.tmdbId,
        category = category
    )
}
