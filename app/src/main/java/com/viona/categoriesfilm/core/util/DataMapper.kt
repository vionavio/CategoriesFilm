package com.viona.categoriesfilm.core.util

import com.viona.categoriesfilm.core.data.source.response.MovieResponseData
import com.viona.categoriesfilm.core.data.source.response.ReviewResponse
import com.viona.categoriesfilm.core.data.source.response.VideoStreamsResponse
import com.viona.categoriesfilm.core.domain.model.AuthorDetails
import com.viona.categoriesfilm.core.domain.model.Genre
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.model.ReviewItem
import com.viona.categoriesfilm.core.domain.model.VideoStream

object DataMapper {

    fun mapListResponseToDomain(input: List<MovieResponseData>): List<Movie> =
        input.map {
            mapResponseToDomain(it)
        }

    fun mapResponseToDomain(movie: MovieResponseData): Movie =
        Movie(
            id = movie.id,
            posterPath = movie.posterPath,
            backdropPath = movie.backdropPath,
            genreIds = movie.genreIds,
            overview = movie.overview,
            originalLanguage = movie.originalLanguage,
            releaseDate = movie.releaseDate,
            runtime = movie.runtime,
            title = movie.title,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            genres = movie.genres?.map {
                Genre(
                    id = it.id,
                    name = it.name
                )
            }
        )

    fun mapVideoResponseToDomain(videoStreamResponse: VideoStreamsResponse) =
        videoStreamResponse.results.map {
            VideoStream(
                key = it.key,
                site = it.site,
                size = it.size,
                type = it.type,
                official = it.official,
                name = it.name,
                id = it.id,
            )
        }

    fun mapReviewResponseToDomain(reviewResponse: ReviewResponse) =
        reviewResponse.results?.map {
            ReviewItem(
                author = it?.author,
                authorDetails = AuthorDetails(
                    avatarPath = it?.authorDetails?.avatarPath,
                    name = it?.authorDetails?.name,
                    rating = it?.authorDetails?.rating,
                    username = it?.authorDetails?.username
                ),
                updatedAt = it?.updatedAt,
                createdAt = it?.createdAt,
                id = it?.id,
                content = it?.content
            )
        }.orEmpty()


}