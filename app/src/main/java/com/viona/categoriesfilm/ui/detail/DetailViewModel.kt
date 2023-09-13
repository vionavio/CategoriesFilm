package com.viona.categoriesfilm.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viona.categoriesfilm.core.data.Resource
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.model.ReviewItem
import com.viona.categoriesfilm.core.domain.model.VideoStream
import com.viona.categoriesfilm.core.domain.usecase.MovieUseCase
import com.viona.categoriesfilm.util.Constants.SITE_KEY
import com.viona.categoriesfilm.util.Constants.TYPE_KEY
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieUseCase: MovieUseCase
): ViewModel() {
    private val _dataMovie: MutableLiveData<Movie> = MutableLiveData()
    val dataMovie: LiveData<Movie> get() = _dataMovie

    private val _videoMovie: MutableLiveData<VideoStream?> = MutableLiveData()
    val videoMovie: LiveData<VideoStream?> get() = _videoMovie

    private val _videoMovieList: MutableLiveData<List<VideoStream>> = MutableLiveData()
    val videoMovieList: LiveData<List<VideoStream>> get() = _videoMovieList

    private val _reviewMovie: MutableLiveData<List<ReviewItem>> = MutableLiveData()
    val reviewMovie: LiveData<List<ReviewItem>> get() = _reviewMovie

    fun getDetailMovie(id: Int) = viewModelScope.launch {
        movieUseCase.getDetailMovie(id).collect {resource ->
            when (resource) {
                is Resource.Success -> {
                    _dataMovie.value = resource.data!!
                }
                is Resource.Error -> {
                }
                else -> {}
            }

        }
    }

    fun getVideoMovie(id: Long) = viewModelScope.launch {
        movieUseCase.getVideoMovie(id).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    val trailer = resource.data?.firstOrNull {
                        it.site == SITE_KEY && it.official && it.type == TYPE_KEY
                    }
                    _videoMovieList.value = resource.data.orEmpty()
                    _videoMovie.value = trailer
                }
                is Resource.Error -> {}
                else -> {}
            }
        }
    }
    fun getReviewMovie(id: Int) = viewModelScope.launch {
        movieUseCase.getReviewMovie(id).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    _reviewMovie.value = resource.data.orEmpty()
                }
                is Resource.Error -> {}
                else -> {}
            }
        }
    }
}