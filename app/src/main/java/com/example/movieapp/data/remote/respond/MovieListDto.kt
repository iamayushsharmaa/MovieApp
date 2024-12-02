package com.example.movieapp.data.remote.respond

data class MovieListDto(
    val page: Int,
    val results: List<MovieList>,
    val total_pages: Int,
    val total_results: Int
)