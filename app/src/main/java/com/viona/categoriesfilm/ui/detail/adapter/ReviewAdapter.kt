package com.viona.categoriesfilm.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viona.categoriesfilm.core.domain.model.ReviewItem
import com.viona.categoriesfilm.databinding.ItemReviewBinding
import com.viona.categoriesfilm.util.DiffUtilHelper


class ReviewAdapter : RecyclerView.Adapter<ReviewViewHolder>() {
    private val diffUtilHelper = DiffUtilHelper<ReviewItem>(this)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(
            diffUtilHelper.differ.currentList[position],
        )
    }

    fun submitData(data: List<ReviewItem>) {
        diffUtilHelper.differ.submitList(data)
    }

    override fun getItemCount(): Int = diffUtilHelper.differ.currentList.size
}