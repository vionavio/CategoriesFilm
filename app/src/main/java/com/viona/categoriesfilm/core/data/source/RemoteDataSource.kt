package com.viona.categoriesfilm.core.data.source

import android.util.Log
import com.viona.categoriesfilm.core.data.source.network.ApiResponse
import com.viona.categoriesfilm.core.data.source.network.ApiService
import com.viona.categoriesfilm.core.data.source.response.MovieResponseData
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
}