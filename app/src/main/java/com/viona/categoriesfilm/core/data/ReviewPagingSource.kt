package com.viona.categoriesfilm.core.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.viona.categoriesfilm.core.data.source.RemoteDataSource
import com.viona.categoriesfilm.core.data.source.network.ApiResponse
import com.viona.categoriesfilm.core.domain.model.ReviewItem
import com.viona.categoriesfilm.core.util.DataMapper
import javax.inject.Inject

class ReviewPagingSource @Inject constructor(
    private val id: Int,
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, ReviewItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewItem> {
        val page = params.key ?: 1
        return try {
            when (val response = remoteDataSource.getReviewMoviePaging(id, page)) {
                is ApiResponse.Success -> {
                    val data = response.data
                    val review = DataMapper.mapListReviewResponseToDomain(data)

                    val prevKey = if (page == 1) null else page - 1
                    val nextKey = if (review.isNotEmpty()) page + 1 else null

                    LoadResult.Page(
                        data = review,
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

    override fun getRefreshKey(state: PagingState<Int, ReviewItem>): Int? {
        return null
    }
}