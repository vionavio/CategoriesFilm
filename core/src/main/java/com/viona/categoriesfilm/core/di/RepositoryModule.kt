package com.viona.categoriesfilm.core.di

import com.viona.categoriesfilm.core.data.MovieRepositoryImpl
import com.viona.categoriesfilm.core.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepositoryImpl: MovieRepositoryImpl):
            MovieRepository
}