package com.viona.categoriesfilm.ui.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.model.ReviewItem
import com.viona.categoriesfilm.core.domain.model.type.MovieType
import com.viona.categoriesfilm.core.domain.usecase.MovieUseCase

class ReviewViewModel(
    private val movieUseCase: MovieUseCase
) :ViewModel() {
    fun getReviewPaging(id: Int): LiveData<PagingData<ReviewItem>> {
        return movieUseCase.getReviewMoviePaging(id).cachedIn(viewModelScope).asLiveData()
    }
}