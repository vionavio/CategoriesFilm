package com.viona.categoriesfilm.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.viona.categoriesfilm.core.data.source.RemoteDataSource
import com.viona.categoriesfilm.core.data.source.network.ApiResponse
import com.viona.categoriesfilm.core.data.source.response.MovieResponseData
import com.viona.categoriesfilm.core.data.source.response.ReviewResponse
import com.viona.categoriesfilm.core.data.source.response.VideoStreamsResponse
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.model.ReviewItem
import com.viona.categoriesfilm.core.domain.model.VideoStream
import com.viona.categoriesfilm.core.domain.model.type.MovieType
import com.viona.categoriesfilm.core.domain.repository.MovieRepository
import com.viona.categoriesfilm.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override fun getPopularMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResponse<List<Movie>, List<MovieResponseData>>() {
            override suspend fun mapApiResponseToResult(responseData: List<MovieResponseData>): List<Movie> {
                return DataMapper.mapListResponseToDomain(responseData)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponseData>>> {
                return remoteDataSource.getPopularMovie()
            }
        }.asFlow()

    override fun getUpcomingMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResponse<List<Movie>, List<MovieResponseData>>() {
            override suspend fun mapApiResponseToResult(responseData: List<MovieResponseData>): List<Movie> {
                return DataMapper.mapListResponseToDomain(responseData)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponseData>>> {
                return remoteDataSource.getUpcomingMovie()
            }
        }.asFlow()

    override fun getTheatreMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResponse<List<Movie>, List<MovieResponseData>>() {
            override suspend fun mapApiResponseToResult(responseData: List<MovieResponseData>): List<Movie> {
                return DataMapper.mapListResponseToDomain(responseData)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponseData>>> {
                return remoteDataSource.getTheatreMovie()
            }
        }.asFlow()

    override fun getMoviePaging(type: MovieType): Flow<PagingData<Movie>> {
        val pagingSourceFactory = { MoviePagingSource(type, remoteDataSource) }

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it }
        }
    }

    override fun getDetailMovie(id: Int): Flow<Resource<Movie>> =
        object : NetworkBoundResponse<Movie, MovieResponseData>() {
            override suspend fun mapApiResponseToResult(responseData: MovieResponseData): Movie {
                return DataMapper.mapResponseToDomain(responseData)
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieResponseData>> {
                return remoteDataSource.getDetailMovie(id)
            }
        }.asFlow()

    override fun getVideoMovie(id: Long): Flow<Resource<List<VideoStream>>> =
        object : NetworkBoundResponse<List<VideoStream>, VideoStreamsResponse>() {
            override suspend fun mapApiResponseToResult(responseData: VideoStreamsResponse): List<VideoStream> {
                return DataMapper.mapVideoResponseToDomain(responseData)
            }

            override suspend fun createCall(): Flow<ApiResponse<VideoStreamsResponse>> {
                return remoteDataSource.getVideoMovie(id)
            }

        }.asFlow()

    override fun getReviewMovie(id: Int): Flow<Resource<List<ReviewItem>>> =
        object : NetworkBoundResponse<List<ReviewItem>, ReviewResponse>() {
            override suspend fun mapApiResponseToResult(responseData: ReviewResponse): List<ReviewItem> {
                return DataMapper.mapReviewResponseToDomain(responseData)
            }

            override suspend fun createCall(): Flow<ApiResponse<ReviewResponse>> {
                return remoteDataSource.getReviewMovie(id)
            }

        }.asFlow()

}