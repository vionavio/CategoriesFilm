package com.viona.categoriesfilm.di

import com.viona.categoriesfilm.core.di.CoreComponent
import com.viona.categoriesfilm.ui.detail.DetailFragment
import com.viona.categoriesfilm.ui.home.HomeFragment
import com.viona.categoriesfilm.ui.list.MoviesFragment
import com.viona.categoriesfilm.ui.review.ReviewFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class],
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: MoviesFragment)
    fun inject(fragment: DetailFragment)
    fun inject(fragment: ReviewFragment)
}
