package com.viona.categoriesfilm.ui.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.viona.categoriesfilm.core.domain.model.ReviewItem
import com.viona.categoriesfilm.databinding.ItemReviewBinding
import com.viona.categoriesfilm.util.Constants
import com.viona.categoriesfilm.util.loadWithGlideCircle

class ReviewViewHolder(
    private val itemBinding: ItemReviewBinding
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(review: ReviewItem) {

        itemBinding.apply {
            val url = Constants.getAvatarUrl(review.authorDetails?.avatarPath.orEmpty())
            ivUsername.loadWithGlideCircle(url)
            tvUsername.text = review.author
            tvContent.text = review.content
        }
    }
}