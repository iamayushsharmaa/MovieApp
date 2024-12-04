package com.example.movieapp.data

import com.example.movieapp.data.local.movie.model.MovieEntity
import com.example.movieapp.data.remote.respond.MovieList
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.utils.JsonUtil


fun MovieList.toMovieEntity(showType: String): MovieEntity {
    return MovieEntity(
        itemType = this.itemType,
        showType = showType,
        id = this.id,
        imdbId = this.imdbId,
        tmdbId = this.tmdbId,
        title = this.title,
        overview = this.overview,
        releaseYear = this.releaseYear,
        originalTitle = this.originalTitle,
        genres = this.genres,
        directors = JsonUtil.jsonToList(this.directors), // Convert JSON to list
        cast = JsonUtil.jsonToList(this.cast),
        rating = this.rating,
        runtime = this.runtime,
        imageSet = this.imageSet
    )
}
fun MovieEntity.toMovie(showType: String): Movie {
    return Movie(
        itemType = this.itemType,
        showType = showType,
        id = this.id,
        imdbId = this.imdbId,
        tmdbId = this.tmdbId,
        title = this.title,
        overview = this.overview,
        releaseYear = this.releaseYear,
        originalTitle = this.originalTitle,
        genres = this.genres,
        directors = this.directors, // Room will handle conversion via TypeConverter
        cast = this.cast,           // Room will handle conversion via TypeConverter
        rating = this.rating,
        runtime = this.runtime,
        imageSet = this.imageSet
    )
}