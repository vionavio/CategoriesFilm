package com.viona.categoriesfilm.core.data

import com.viona.categoriesfilm.core.data.source.RemoteDataSource
import com.viona.categoriesfilm.core.data.source.network.ApiResponse
import com.viona.categoriesfilm.core.data.source.response.MovieResponseData
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.repository.MovieRepository
import com.viona.categoriesfilm.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): MovieRepository {
    override fun getPopularMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResponse<List<Movie>, List<MovieResponseData>>() {
            override suspend fun mapApiResponseToResult(responseData: List<MovieResponseData>): List<Movie> {
                return DataMapper.mapResponseToDomain(responseData)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponseData>>> {
                return remoteDataSource.getPopularMovie()
            }
        }.asFlow()

    override fun getUpcomingMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResponse<List<Movie>, List<MovieResponseData>>() {
            override suspend fun mapApiResponseToResult(responseData: List<MovieResponseData>): List<Movie> {
                return DataMapper.mapResponseToDomain(responseData)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponseData>>> {
                return remoteDataSource.getUpcomingMovie()
            }
        }.asFlow()

    override fun getTheatreMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResponse<List<Movie>, List<MovieResponseData>>() {
            override suspend fun mapApiResponseToResult(responseData: List<MovieResponseData>): List<Movie> {
                return DataMapper.mapResponseToDomain(responseData)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponseData>>> {
                return remoteDataSource.getTheatreMovie()
            }
        }.asFlow()

}