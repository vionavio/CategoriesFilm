package com.viona.categoriesfilm.core.util

import com.viona.categoriesfilm.core.data.source.response.MovieResponseData
import com.viona.categoriesfilm.core.domain.model.Movie

object DataMapper {

    fun mapResponseToDomain(input: List<MovieResponseData>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                releaseDate = it.releaseDate,
                runtime = it.runtime,
                title = it.title,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount

            )
        }
}