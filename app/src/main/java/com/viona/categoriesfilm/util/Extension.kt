package com.viona.categoriesfilm.util

import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.viona.categoriesfilm.R

fun <T> LiveData<T>.observableData(
    owner: LifecycleOwner,
    action: (T) -> Unit,
) {
    this.observe(owner) { data ->
        action.invoke(data)
    }
}

fun ImageView.loadWithGlide(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_loading_image)
        .error(R.drawable.ic_broken_image)
        .apply(RequestOptions.downsampleOf(DownsampleStrategy.AT_MOST))
        .into(this)
}