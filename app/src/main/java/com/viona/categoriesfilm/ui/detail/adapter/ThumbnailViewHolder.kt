package com.viona.categoriesfilm.ui.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.viona.categoriesfilm.core.domain.model.VideoStream
import com.viona.categoriesfilm.databinding.ItemVideoBinding
import com.viona.categoriesfilm.util.Constants
import com.viona.categoriesfilm.util.loadWithGlide

class ThumbnailViewHolder(
    private val itemBinding: ItemVideoBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(video: VideoStream) {

        itemBinding.apply {
            ivBackdrop.loadWithGlide(Constants.getYoutubeImageUrl(video.key))
        }
    }
}