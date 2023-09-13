package com.viona.categoriesfilm.core.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.viona.categoriesfilm.core.data.source.RemoteDataSource
import com.viona.categoriesfilm.core.data.source.network.ApiResponse
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.model.type.MovieType
import com.viona.categoriesfilm.core.util.DataMapper
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val type: MovieType,
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            when (val response = remoteDataSource.getMoviePaging(type, page)) {
                is ApiResponse.Success -> {
                    val data = response.data
                    val movies = DataMapper.mapListResponseToDomain(data)

                    val prevKey = if (page == 1) null else page - 1
                    val nextKey = if (movies.isNotEmpty()) page + 1 else null

                    LoadResult.Page(
                        data = movies,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }

                is ApiResponse.Error -> {
                    LoadResult.Error(Exception("Error fetching data: ${response.errorMessage}"))
                }

                ApiResponse.Empty -> LoadResult.Page(emptyList(), null, null)
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }
}