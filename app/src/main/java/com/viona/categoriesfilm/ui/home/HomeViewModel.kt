package com.viona.categoriesfilm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.viona.categoriesfilm.core.data.Resource
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _popularMovie: MutableLiveData<List<Movie>> = MutableLiveData()
    val popularMovie: LiveData<List<Movie>> get() = _popularMovie

    private val _upcomingMovie: MutableLiveData<List<Movie>> = MutableLiveData()
    val upcomingMovie: LiveData<List<Movie>> get() = _upcomingMovie

    private val _theatreMovie: MutableLiveData<List<Movie>> = MutableLiveData()
    val theatreMovie: LiveData<List<Movie>> get() = _theatreMovie

    private val _highlightMovie: MutableLiveData<Movie> = MutableLiveData()
    val highlightMovie: LiveData<Movie> get() = _highlightMovie

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getPopularMovie() = viewModelScope.launch {
        movieUseCase.getPopularMovie().collect{ resource ->
            when (resource) {
                is Resource.Success -> {
                    _popularMovie.value = resource.data.orEmpty()
                    _highlightMovie.value = resource.data?.firstOrNull()
                }
                is Resource.Error -> {
                    _errorMessage.value = resource.message.orEmpty()
                }
                else -> {}
            }

        }
    }

    fun getUpcomingMovie() = viewModelScope.launch {
        movieUseCase.getUpcomingMovie().collect{ resource ->
            when (resource) {
                is Resource.Success -> {
                    _upcomingMovie.value = resource.data.orEmpty()
                }
                is Resource.Error -> {
                    _errorMessage.value = resource.message.orEmpty()
                }
                else -> {}
            }

        }
    }

    fun getTheatreMovie() = viewModelScope.launch {
        movieUseCase.getTheatreMovie().collect{ resource ->
            when (resource) {
                is Resource.Success -> {
                    _theatreMovie.value = resource.data.orEmpty()
                }
                is Resource.Error -> {
                    _errorMessage.value = resource.message.orEmpty()
                }
                else -> {}
            }

        }
    }
}