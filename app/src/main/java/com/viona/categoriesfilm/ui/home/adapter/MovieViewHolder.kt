package com.viona.categoriesfilm.ui.home.adapter

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.viona.categoriesfilm.R
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.databinding.ItemMovieBinding
import com.viona.categoriesfilm.util.Constants
import com.viona.categoriesfilm.util.Constants.EXTRA_ID
import com.viona.categoriesfilm.util.Constants.EXTRA_TITLE
import com.viona.categoriesfilm.util.loadWithGlide

class MovieViewHolder(
    private val itemBinding: ItemMovieBinding
): RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: Movie) {

        itemBinding.apply {
            clContainer.setOnClickListener {
                val bundle = Bundle().apply {
                    putInt(EXTRA_ID, movie.id)
                    putString(EXTRA_TITLE, movie.title)
                }
                it.findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            }
            ivMovie.loadWithGlide(Constants.getPosterUrl(movie.posterPath.orEmpty()))
            tvTitle.text = movie.title
        }
    }
}