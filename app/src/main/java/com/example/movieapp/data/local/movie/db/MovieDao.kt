package com.example.movieapp.data.local.movie.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.movieapp.data.local.movie.model.MovieEntity

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)

    @Query("SELECT * FROM movieEntity WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * FROM movieEntity WHERE showType = :showType")
    suspend fun getShowByType(showType : String): List<MovieEntity>

}