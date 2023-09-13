package com.viona.categoriesfilm.ui.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.viona.categoriesfilm.core.domain.model.ReviewItem
import com.viona.categoriesfilm.databinding.ItemReviewDetailBinding

class ReviewAdapter : PagingDataAdapter<ReviewItem, ReviewViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding =
            ItemReviewDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<ReviewItem>() {
            override fun areItemsTheSame(oldItem: ReviewItem, newItem: ReviewItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ReviewItem, newItem: ReviewItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}