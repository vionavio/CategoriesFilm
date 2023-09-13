package com.viona.categoriesfilm.core.data.source.response

import com.google.gson.annotations.SerializedName
data class GenreResponseData(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,
)