package com.levent.studycase.movies

import android.annotation.SuppressLint
import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.levent.studycase.R
import com.levent.studycase.databinding.ItemMovieBinding
import com.levent.studycase.models.Movie

class MovieAdapter(private val context:Context, private val movies: List<Movie>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(context,movie)

        holder.itemView.setOnClickListener{

            onItemClick(movie.title)

        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(context: Context,movie: Movie) {
            binding.titleTextView.text = movie.title

            val formattedString = String.format("%.1f", movie.voteAverage)
            binding.ratingTextView.text = "${formattedString} / 10"

            binding.dateTextView.text = movie.releaseDate.split("-")[0]

            if (movie.voteAverage < 7) {
                with(binding) {
                    ratingTextView.setTextColor(context.getColor(R.color.red))
                    imageView.setColorFilter(context.getColor(R.color.red))
                }
            } else if (movie.voteAverage in 7.0..9.0) {
                with(binding) {
                    imageView.setColorFilter(context.getColor(R.color.orange))
                    ratingTextView.setTextColor(context.getColor(R.color.orange))
                }
            } else {
               with(binding) {
                   ratingTextView.setTextColor(context.getColor(R.color.green))
                   imageView.setColorFilter(context.getColor(R.color.green))
               }
            }

            val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

            val moviePosterURL = POSTER_BASE_URL + movie.posterPath

            Glide.with(binding.root)
                .load(moviePosterURL)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.posterImageView)
        }
    }
}