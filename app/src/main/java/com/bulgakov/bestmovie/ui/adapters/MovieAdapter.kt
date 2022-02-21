package com.bulgakov.bestmovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bulgakov.bestmovie.databinding.ItemMovieBinding
import com.bulgakov.bestmovie.model.Movie
import com.bulgakov.bestmovie.ui.activities.MainActivity
import com.bumptech.glide.Glide


class MovieAdapter :
    PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            with(holder.binding) {
                title.text = movie.title
                description.text = movie.description
                Glide.with(image.context)
                    .load(movie.imageUrl)
                    .into(image)
            }
        }
    }

    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}