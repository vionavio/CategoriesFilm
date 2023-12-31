package com.viona.categoriesfilm.core.domain.model


data class ReviewItem(
	val authorDetails: AuthorDetails? = null,
	val updatedAt: String? = null,
	val author: String? = null,
	val createdAt: String? = null,
	val id: String? = null,
	val content: String? = null,
	val url: String? = null
)

data class AuthorDetails(
	val avatarPath: String? = "",
	val name: String? = "",
	val rating: Any? = "",
	val username: String? = ""
)
