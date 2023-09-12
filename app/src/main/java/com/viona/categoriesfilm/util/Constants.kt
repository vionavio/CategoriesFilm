package com.viona.categoriesfilm.util

object Constants {
    private const val BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
    private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w185"

    fun getPosterUrl(path: String) = BASE_POSTER_URL + path
}