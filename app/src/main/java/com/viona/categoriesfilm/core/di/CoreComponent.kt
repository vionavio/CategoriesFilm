package com.viona.categoriesfilm.core.di

import com.viona.categoriesfilm.core.domain.repository.MovieRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class],
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(): CoreComponent
    }

    fun provideRepository(): MovieRepository
}
