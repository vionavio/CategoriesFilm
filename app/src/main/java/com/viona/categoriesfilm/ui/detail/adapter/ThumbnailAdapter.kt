package com.viona.categoriesfilm.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viona.categoriesfilm.core.domain.model.VideoStream
import com.viona.categoriesfilm.databinding.ItemVideoBinding
import com.viona.categoriesfilm.util.DiffUtilHelper

class ThumbnailAdapter : RecyclerView.Adapter<ThumbnailViewHolder>() {
    private val diffUtilHelper = DiffUtilHelper<VideoStream>(this)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThumbnailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        holder.bind(
            diffUtilHelper.differ.currentList[position],
        )
    }

    fun submitData(data: List<VideoStream>) {
        diffUtilHelper.differ.submitList(data)
    }

    override fun getItemCount(): Int = diffUtilHelper.differ.currentList.size



}