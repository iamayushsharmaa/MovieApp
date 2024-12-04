package com.example.movieapp.data.remote.respond

data class Poster(
    val w240: String,
    val w360: String,
    val w480: String,
    val w600: String,
    val w720: String,
    val w1080: String? // Nullable for cases where some sizes might be missing
)