package com.viona.categoriesfilm.core.domain.repository

import com.viona.categoriesfilm.core.data.Resource
import com.viona.categoriesfilm.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovie(): Flow<Resource<List<Movie>>>
    fun getUpcomingMovie(): Flow<Resource<List<Movie>>>
    fun getTheatreMovie(): Flow<Resource<List<Movie>>>
}