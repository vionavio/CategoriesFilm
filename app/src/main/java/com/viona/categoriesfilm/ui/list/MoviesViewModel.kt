package com.viona.categoriesfilm.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.model.type.MovieType
import com.viona.categoriesfilm.core.domain.usecase.MovieUseCase

class MoviesViewModel(
    private val movieUseCase: MovieUseCase
): ViewModel() {

    fun getMoviePaging(type: MovieType): LiveData<PagingData<Movie>> {
        return movieUseCase.getMoviePaging(type).cachedIn(viewModelScope).asLiveData()
    }
}