package com.viona.categoriesfilm.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.databinding.ItemMovieBinding
import com.viona.categoriesfilm.util.Constants
import com.viona.categoriesfilm.util.loadWithGlide

class MovieViewHolder(
    private val itemBinding: ItemMovieBinding
): RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: Movie) {

        itemBinding.apply {
            ivMovie.loadWithGlide(Constants.getPosterUrl(movie.posterPath.orEmpty()))
            tvTitle.text = movie.title
        }
    }
}