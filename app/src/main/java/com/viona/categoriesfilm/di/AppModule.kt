package com.viona.categoriesfilm.di

import com.viona.categoriesfilm.core.domain.usecase.MovieUseCase
import com.viona.categoriesfilm.core.domain.usecase.MovieUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(
        movieUseCaseImpl: MovieUseCaseImpl,
    ): MovieUseCase
}
