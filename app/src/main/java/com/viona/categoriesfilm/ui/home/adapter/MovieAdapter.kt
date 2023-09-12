package com.viona.categoriesfilm.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.databinding.ItemMovieBinding
import com.viona.categoriesfilm.util.DiffUtilHelper

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private val diffUtilHelper = DiffUtilHelper<Movie>(this)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(
            diffUtilHelper.differ.currentList[position],
        )
    }

    fun submitData(data: List<Movie>) {
        diffUtilHelper.differ.submitList(data)
    }

    override fun getItemCount(): Int = diffUtilHelper.differ.currentList.size



}