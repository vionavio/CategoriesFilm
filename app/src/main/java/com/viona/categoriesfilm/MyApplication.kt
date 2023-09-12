package com.viona.categoriesfilm

import android.app.Application
import com.viona.categoriesfilm.core.di.CoreComponent
import com.viona.categoriesfilm.core.di.DaggerCoreComponent
import com.viona.categoriesfilm.di.AppComponent
import com.viona.categoriesfilm.di.DaggerAppComponent

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}
