package com.viona.categoriesfilm.ui.home

import android.util.Log
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

    fun getPopularMovie() = viewModelScope.launch {
        movieUseCase.getPopularMovie().collect{ resource ->
            when (resource) {
                is Resource.Success -> {
                    _popularMovie.value = resource.data.orEmpty()
                    Log.d("aaaaaaa", resource.data.toString() )
                }
                is Resource.Error -> {
                    Log.d("aaaaaerr", resource.message.toString())
                }
                else -> {}
            }

        }
    }
}