package com.viona.categoriesfilm.core.data.source.network

import com.viona.categoriesfilm.core.data.source.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(@Query("page") page: Int): MovieResponse

    @GET("movie/now_playing")
    suspend fun getTheatreMovie(@Query("page") page: Int): MovieResponse



}
