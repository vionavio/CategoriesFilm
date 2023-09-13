package com.viona.categoriesfilm.core.data.source

import android.util.Log
import com.viona.categoriesfilm.core.data.source.network.ApiResponse
import com.viona.categoriesfilm.core.data.source.network.ApiService
import com.viona.categoriesfilm.core.data.source.response.MovieResponseData
import com.viona.categoriesfilm.core.data.source.response.ResultsItemResponseData
import com.viona.categoriesfilm.core.data.source.response.ReviewResponse
import com.viona.categoriesfilm.core.data.source.response.VideoStreamsResponse
import com.viona.categoriesfilm.core.domain.model.type.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getPopularMovie(): Flow<ApiResponse<List<MovieResponseData>>> {
        return flow {
            try {
                val response = apiService.getPopularMovie(1)
                val dataList = response.results.orEmpty()
                if (dataList.isNotEmpty()) {
                    emit(ApiResponse.Success(dataList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUpcomingMovie(): Flow<ApiResponse<List<MovieResponseData>>> {
        return flow {
            try {
                val response = apiService.getUpcomingMovie(1)
                val dataList = response.results.orEmpty()
                if (dataList.isNotEmpty()) {
                    emit(ApiResponse.Success(dataList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTheatreMovie(): Flow<ApiResponse<List<MovieResponseData>>> {
        return flow {
            try {
                val response = apiService.getTheatreMovie(1)
                val dataList = response.results.orEmpty()
                if (dataList.isNotEmpty()) {
                    emit(ApiResponse.Success(dataList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMoviePaging(
        type: MovieType,
        page: Int
    ): ApiResponse<List<MovieResponseData>> {
        try {
            val response = when (type) {
                MovieType.POPULAR -> apiService.getPopularMovie(page)
                MovieType.UPCOMING -> apiService.getUpcomingMovie(page)
                MovieType.THEATRE -> apiService.getTheatreMovie(page)
            }
            val data = response.results
            if (data != null) {
                return ApiResponse.Success(data)
            }
            return ApiResponse.Error("Failed to fetch popular movies")
        } catch (e: Exception) {
            return ApiResponse.Error("An error occurred: ${e.message}")
        }
    }

    suspend fun getDetailMovie(id: Int): Flow<ApiResponse<MovieResponseData>> {
        return flow {
            try {
                val response = apiService.getDetailMovie(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getVideoMovie(id: Long): Flow<ApiResponse<VideoStreamsResponse>> {
        return flow {
            try {
                val response = apiService.getVideoStreams(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getReviewMovie(id: Int): Flow<ApiResponse<ReviewResponse>> {
        return flow {
            try {
                val response = apiService.getReviewMovie(id, 1)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getReviewMoviePaging(
        id: Int,
        page: Int
    ): ApiResponse<List<ResultsItemResponseData>> {
        try {
            val response = apiService.getReviewMovie(id, page)
            val data = response.results
            if (data != null) {
                return ApiResponse.Success(data)
            }
            return ApiResponse.Error("Failed to fetch popular movies")
        } catch (e: Exception) {
            return ApiResponse.Error("An error occurred: ${e.message}")
        }
    }
}