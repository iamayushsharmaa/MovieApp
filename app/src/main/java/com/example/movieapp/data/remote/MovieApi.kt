package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.respond.MovieList
import com.example.movieapp.data.remote.respond.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {


    @GET("shows/{showType}")
    fun getMoviesList(
        @Path("showType") showType: String,
        @Query("page") page : Int,
        @Query("api_key") apiKey : String = API_KEY
    ) : MovieListDto


    companion object{
        const val BASE_URL = "https://streaming-availability.p.rapidapi.com/"
        const val API_KEY = "0d0b640a07msh8ab9df507a6a5fcp1a286djsn00752772672e"
    }


}