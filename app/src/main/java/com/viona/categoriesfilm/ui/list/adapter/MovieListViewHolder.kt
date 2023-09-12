package com.viona.categoriesfilm.ui.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.databinding.ItemMovieGridBinding
import com.viona.categoriesfilm.util.Constants
import com.viona.categoriesfilm.util.loadWithGlide


class MovieListViewHolder(
    private val itemBinding: ItemMovieGridBinding
): RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: Movie) {

        itemBinding.apply {
            ivPoster.loadWithGlide(Constants.getPosterUrl(movie.posterPath.orEmpty()))
            tvTitle.text = movie.title
        }
    }
}