package com.viona.categoriesfilm.util

object Constants {
    private const val BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
    private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w185"
    private const val BASE_YT_IMG_URL = "https://img.youtube.com/vi/"
    private const val BASE_PROFILE_URL = "https://image.tmdb.org/t/p/w185"
    const val EXTRA_TYPE = "type_movies"
    const val EXTRA_ID = "id_movies"
    const val SITE_KEY = "YouTube"
    const val TYPE_KEY = "Trailer"

    const val MAX_RATING = 10f

    fun getPosterUrl(path: String) = BASE_POSTER_URL + path
    fun getBackdropUrl(path: String) = BASE_BACKDROP_URL + path
    fun getAvatarUrl(path: String) = BASE_PROFILE_URL + path

    fun getRuntimeDateFormat() = ("yyyy-MM-dd")

    fun getYoutubeImageUrl(youtubeId: String) = "$BASE_YT_IMG_URL$youtubeId/hqdefault.jpg"
}