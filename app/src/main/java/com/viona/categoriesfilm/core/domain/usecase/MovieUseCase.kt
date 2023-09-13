package com.viona.categoriesfilm.core.domain.usecase

import androidx.paging.PagingData
import com.viona.categoriesfilm.core.data.Resource
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.model.ReviewItem
import com.viona.categoriesfilm.core.domain.model.VideoStream
import com.viona.categoriesfilm.core.domain.model.type.MovieType
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getPopularMovie(): Flow<Resource<List<Movie>>>
    fun getUpcomingMovie(): Flow<Resource<List<Movie>>>
    fun getTheatreMovie(): Flow<Resource<List<Movie>>>
    fun getMoviePaging(type: MovieType): Flow<PagingData<Movie>>
    fun getDetailMovie(id: Int): Flow<Resource<Movie>>
    fun getVideoMovie(id: Long): Flow<Resource<List<VideoStream>>>
    fun getReviewMovie(id: Int): Flow<Resource<List<ReviewItem>>>
    fun getReviewMoviePaging(id: Int): Flow<PagingData<ReviewItem>>
}