package com.viona.categoriesfilm.ui.review.adapter

import androidx.recyclerview.widget.RecyclerView
import com.viona.categoriesfilm.core.domain.model.ReviewItem
import com.viona.categoriesfilm.databinding.ItemReviewDetailBinding
import com.viona.categoriesfilm.util.Constants
import com.viona.categoriesfilm.util.loadWithGlideCircle


class ReviewViewHolder(
    private val itemBinding: ItemReviewDetailBinding
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(review: ReviewItem) {

        itemBinding.apply {
            val url = Constants.getAvatarUrl(review.authorDetails?.avatarPath.orEmpty())
            ivUsername.loadWithGlideCircle(url)
            tvUsername.text = review.author
            tvContent.text = review.content
            tvName.text = review.authorDetails?.name
            if (review.authorDetails?.rating.toString().isNullOrEmpty()) return
            ratingBar.rating = 5 * ((review.authorDetails?.rating.toString().toFloat() / Constants.MAX_RATING))
        }
    }
}