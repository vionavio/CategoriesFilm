package com.viona.categoriesfilm.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.viona.categoriesfilm.core.domain.model.Movie
import com.viona.categoriesfilm.databinding.ItemMovieGridBinding

class MovieListAdapter : PagingDataAdapter<Movie, MovieListViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding =
            ItemMovieGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}