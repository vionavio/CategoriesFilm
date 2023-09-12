package com.viona.categoriesfilm.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class DiffUtilHelper<T : Any>(private val adapter: RecyclerView.Adapter<*>) {
    private val diffCallback = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem
    }
    val differ = AsyncListDiffer(adapter, diffCallback)
}
