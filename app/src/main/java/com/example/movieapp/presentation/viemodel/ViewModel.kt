package com.example.movieapp.presentation.viemodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.repository.MovieListRepository
import com.example.movieapp.utils.Resource
import com.example.movieapp.utils.ShowType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
) : ViewModel(){

    private val _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    init {
        getMovies(false)
        getSeries(false)
    }

    fun onEvent(event: MovieListUiEvent){
        when(event){
            is MovieListUiEvent.Navigate -> {
                _movieListState.update {
                    it.copy(
                        isCurrentMovieScreen = !movieListState.value.isCurrentMovieScreen
                    )
                }
            }
            is MovieListUiEvent.Paginate ->{
                if (event.showType == ShowType.MOVIE){
                    getMovies(forceFetchFromRemote = true)
                }else if (event.showType == ShowType.SERIES){
                    getSeries(forceFetchFromRemote = true)
                }

            }
        }
    }

    private fun getSeries( forceFetchFromRemote: Boolean) {

        viewModelScope.launch {
            Log.d("getSeries", "Function called with forceFetchFromRemote: $forceFetchFromRemote")
            _movieListState.update {
                Log.d("getSeries", "Setting isLoading to true")
                it.copy(isLoading = true)
            }
            movieListRepository.getShowList(
                forceFetchFromRemote,
                ShowType.SERIES
            ).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        Log.d("getMovies", "Resource is in Loading state")
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        Log.d("getMovies", "Resource is in Success state")
                        result.data?.let { seriesList ->
                            Log.d("getMovies", "Movies fetched: ${seriesList.size} items")
                            _movieListState.update {
                                it.copy(
                                    movieList = movieListState.value.movieList + seriesList.shuffled(),
                                )
                            }
                        }
                    }
                    is Resource.Error -> {
                        // Log for error state
                        Log.e("getMovies", "Error occurred: ${result.message}")
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }

    private fun getMovies(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            Log.d("getMovies", "Function called with forceFetchFromRemote: $forceFetchFromRemote")
            _movieListState.update {
                Log.d("getMovies", "Setting isLoading to true")
                it.copy(isLoading = true)
            }
            movieListRepository.getShowList(
                forceFetchFromRemote,
                ShowType.MOVIE
                ).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        Log.d("getMovies", "Resource is in Loading state")
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Success -> {
                        Log.d("getMovies", "Resource is in Success state")
                        result.data?.let { movieList ->
                            Log.d("getMovies", "Movies fetched: ${movieList.size} items")
                            _movieListState.update {
                                it.copy(
                                    movieList = movieListState.value.movieList + movieList.shuffled(),
                                )
                            }
                        }
                    }
                    is Resource.Error -> {
                        Log.e("getMovies", "Error occurred: ${result.message}")
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }


}