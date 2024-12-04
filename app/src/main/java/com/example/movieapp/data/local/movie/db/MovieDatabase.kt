package com.example.movieapp.data.local.movie.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieapp.data.local.movie.conveter.Converters
import com.example.movieapp.data.local.movie.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao : MovieDao
}