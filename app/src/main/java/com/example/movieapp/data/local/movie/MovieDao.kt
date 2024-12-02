package com.example.movieapp.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)

    @Query("SELECT * FROM movie_entity WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * FROM movie_entity WHERE category = :category")
    suspend fun getMovieCategory(category : String): List<MovieEntity>

    @Query("SELECT * FROM movie_entity WHERE country = :country")
    suspend fun getMovieByLanguage(country : String): List<MovieEntity>
}