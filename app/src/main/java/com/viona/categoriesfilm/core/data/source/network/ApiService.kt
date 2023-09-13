package com.viona.categoriesfilm.core.data.source.network

import com.viona.categoriesfilm.core.data.source.response.MovieResponse
import com.viona.categoriesfilm.core.data.source.response.MovieResponseData
import com.viona.categoriesfilm.core.data.source.response.VideoStreamsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(@Query("page") page: Int): MovieResponse

    @GET("movie/now_playing")
    suspend fun getTheatreMovie(@Query("page") page: Int): MovieResponse

    @GET("movie/{id}")
    suspend fun getDetailMovie(@Path("id") id: Int): MovieResponseData

    @GET("movie/{movie_id}/videos")
    suspend fun getVideoStreams(
        @Path("movie_id") movieId: Long,
    ): VideoStreamsResponse



}
