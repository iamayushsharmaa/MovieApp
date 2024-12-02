package com.example.movieapp.data.repository

import coil3.network.HttpException
import com.example.movieapp.data.local.movie.MovieDatabase
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.data.toMovie
import com.example.movieapp.data.toMovieEntity
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieListRepository
import com.example.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase : MovieDatabase
) : MovieListRepository{
    
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))
            val localMovieList = movieDatabase.movieDao.getMovieCategory(category = category)

            val shouldJustLoadFromCache = !forceFetchFromRemote && localMovieList.isNotEmpty()

            if (shouldJustLoadFromCache){
                emit(Resource.Success(
                    data = localMovieList.map {movieEntity ->
                        movieEntity.toMovie(category)

                    }
                ))
                emit(Resource.Loading(false))
                return@flow
            }
            val movieListFromApi = try {
                movieApi.getMoviesList(category,page)
            }catch (e : IOException){
                e.printStackTrace()
                emit(Resource.Error("Error Loading Movies"))
                return@flow
            }catch (e : HttpException){
                e.printStackTrace()
                emit(Resource.Error("Error Loading Movies"))
                return@flow
            }catch (e : Exception){
                e.printStackTrace()
                emit(Resource.Error("Error Loading Movies"))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movieList->
                    movieList.toMovieEntity(category)
                }
            }

            movieDatabase.movieDao.upsertMovieList(movieEntities)

            emit(
                Resource.Success(
                    movieEntities.map { it.toMovie(category) }
                )
            )

        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        return flow {
            emit(Resource.Loading(true))

            val movieEntity = movieDatabase.movieDao.getMovieById(id)

            if (movieEntity != null){
                emit(
                    Resource.Success(movieEntity.toMovie(movieEntity.category))
                )
                emit(Resource.Loading(false))
                return@flow
            }

            emit(Resource.Error(message = "Error no such item"))
            emit(Resource.Loading(false))

        }
    }
}