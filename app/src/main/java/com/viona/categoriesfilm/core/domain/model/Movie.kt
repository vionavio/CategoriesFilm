package com.viona.categoriesfilm.core.domain.model

data class Movie(
    val id: Int,

    val posterPath: String?,

    val backdropPath: String?,

    val title: String,

    val voteCount: Int,

    val voteAverage: Float,

    val genreIds: List<Int>?,

    val originalLanguage: String? = "",

    val releaseDate: String?,

    val runtime: Int?,

    val overview: String?,

    val genres: List<Genre>?)