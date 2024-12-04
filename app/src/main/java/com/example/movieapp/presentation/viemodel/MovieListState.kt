package com.example.movieapp.presentation.viemodel

import com.example.movieapp.data.remote.respond.MovieList
import com.example.movieapp.domain.model.Movie

data class MovieListState(
    val isLoading : Boolean = false,
    val isCurrentMovieScreen : Boolean = false,
    val movieList :  List<Movie> = emptyList(),
    val seriesList :  List<Movie> = emptyList()
)
