package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow


interface MovieListRepository {

    suspend fun getMovieList(
        forceFetchFromRemote : Boolean,
        showType : String,
        page :Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id : Int): Flow<Resource<Movie>>
}