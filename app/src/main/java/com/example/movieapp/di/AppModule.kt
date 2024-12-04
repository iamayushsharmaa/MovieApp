package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.data.local.movie.db.MovieDatabase
import com.example.movieapp.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val header = mapOf(
        "Accept" to " application/json",
        "x-rapidapi-key" to "0d0b640a07msh8ab9df507a6a5fcp1a286djsn00752772672e",
        "x-rapidapi-host" to "streaming-availability.p.rapidapi.com"
    )

    val client = OkHttpClient.Builder().apply {
        readTimeout(30, TimeUnit.SECONDS)
        connectTimeout(30, TimeUnit.SECONDS)
        addInterceptor { chain->
            val newReqeust = chain.request().newBuilder().apply {
                header.forEach { key , value ->  addHeader(key, value)}
            }.build()
            chain.proceed(newReqeust)
        }
    }.build()


    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
           // .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .baseUrl(MovieApi.BASE_URL)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "moviedb.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}