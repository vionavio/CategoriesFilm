package com.viona.categoriesfilm.core.data.source.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    var page: Int? = 0,

    @SerializedName("results")
    var results: List<MovieResponseData>? = null
)