package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow


interface MovieListRepository {

    suspend fun getShowList(
        forceFetchFromRemote : Boolean,
        showType : String
    ): Flow<Resource<List<Movie>>>

    suspend fun getShow(id : Int): Flow<Resource<Movie>>
}