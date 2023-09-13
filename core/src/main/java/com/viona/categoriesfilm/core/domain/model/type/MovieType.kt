package com.viona.categoriesfilm.core.domain.model.type

enum class MovieType {
    POPULAR,
    UPCOMING,
    THEATRE;

    companion object {
        fun getTypeByString(title: String) = entries.firstOrNull {
                it.name == title
            } ?: POPULAR
    }
}