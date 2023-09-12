package com.viona.categoriesfilm.core.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.viona.categoriesfilm.core.data.Resource
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.model.type.MovieType
import com.viona.categoriesfilm.core.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {
    override fun getPopularMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopularMovie()
    }

    override fun getUpcomingMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getUpcomingMovie()
    }

    override fun getTheatreMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getTheatreMovie()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getMoviePaging(type: MovieType): Flow<PagingData<Movie>> {
        return movieRepository.getMoviePaging(type).mapLatest { pagingData ->
            pagingData.map { it }
        }
    }

}