package com.example.movieapp.data.local.movie.conveter

import androidx.room.TypeConverter
import com.example.movieapp.data.remote.respond.Genre
import com.example.movieapp.data.remote.respond.ImageSet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromGenreList(genres: List<Genre>): String {
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenreList(data: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromImageSet(imageSet: ImageSet): String {
        return gson.toJson(imageSet)
    }
    @TypeConverter
    fun toImageSet(data: String): ImageSet {
        return gson.fromJson(data, ImageSet::class.java)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
}
