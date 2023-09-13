package com.viona.categoriesfilm.core.domain.repository

import androidx.paging.PagingData
import com.viona.categoriesfilm.core.data.Resource
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.model.VideoStream
import com.viona.categoriesfilm.core.domain.model.type.MovieType
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovie(): Flow<Resource<List<Movie>>>
    fun getUpcomingMovie(): Flow<Resource<List<Movie>>>
    fun getTheatreMovie(): Flow<Resource<List<Movie>>>
    fun getMoviePaging(type: MovieType): Flow<PagingData<Movie>>
    fun getDetailMovie(id: Int): Flow<Resource<Movie>>
    fun getVideoMovie(id: Long): Flow<Resource<List<VideoStream>>>
}