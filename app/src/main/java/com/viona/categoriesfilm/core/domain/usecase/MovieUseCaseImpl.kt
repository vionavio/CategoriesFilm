package com.viona.categoriesfilm.core.domain.usecase

import com.viona.categoriesfilm.core.data.Resource
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {
    override fun getPopularMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopularMovie()
    }

}