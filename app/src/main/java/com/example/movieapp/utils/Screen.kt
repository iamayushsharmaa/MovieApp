package com.example.movieapp.utils



sealed class Screen(val rout: String) {
    object Home : Screen("main")
    object MovieList : Screen("popularMovie")
    object SeriesList : Screen("upcomingMovie")
    object Details : Screen("details")
}