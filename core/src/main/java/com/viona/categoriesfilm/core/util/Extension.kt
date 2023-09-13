package com.viona.categoriesfilm.core.util

import retrofit2.Retrofit

inline fun retrofit(init: Retrofit.Builder.() -> Unit): Retrofit {
    val retrofit = Retrofit.Builder()
    retrofit.init()
    return retrofit.build()
}