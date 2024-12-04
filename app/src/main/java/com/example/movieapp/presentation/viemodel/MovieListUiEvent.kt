package com.example.movieapp.presentation.viemodel

sealed class MovieListUiEvent {
    data class Paginate(val showType : String) : MovieListUiEvent()
    object Navigate : MovieListUiEvent()
}