package com.viona.categoriesfilm.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viona.categoriesfilm.core.domain.usecase.MovieUseCase
import com.viona.categoriesfilm.di.AppScope
import com.viona.categoriesfilm.ui.detail.DetailViewModel
import com.viona.categoriesfilm.ui.home.HomeViewModel
import com.viona.categoriesfilm.ui.list.MoviesViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }


}
