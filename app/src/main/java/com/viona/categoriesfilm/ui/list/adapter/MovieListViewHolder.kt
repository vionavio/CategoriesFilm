package com.viona.categoriesfilm.ui.list.adapter

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.viona.categoriesfilm.R
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.databinding.ItemMovieGridBinding
import com.viona.categoriesfilm.util.Constants
import com.viona.categoriesfilm.util.loadWithGlide


class MovieListViewHolder(
    private val itemBinding: ItemMovieGridBinding
): RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(movie: Movie) {

        itemBinding.apply {
            ivPoster.setOnClickListener {
                val bundle = Bundle().apply {
                    putInt(Constants.EXTRA_ID, movie.id)
                }
                it.findNavController().navigate(R.id.action_moviesFragment_to_detailFragment, bundle)
                Toast.makeText(it.context, movie.title, Toast.LENGTH_SHORT).show()
            }

            ivPoster.loadWithGlide(Constants.getPosterUrl(movie.posterPath.orEmpty()))
            tvTitle.text = movie.title
        }
    }
}