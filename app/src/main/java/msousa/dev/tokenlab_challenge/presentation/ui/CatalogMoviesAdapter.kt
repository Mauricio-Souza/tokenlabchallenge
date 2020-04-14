package msousa.dev.tokenlab_challenge.presentation.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_movies.view.*
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.presentation.extensions.inflate
import msousa.dev.tokenlab_challenge.presentation.extensions.loadFromUrl
import msousa.dev.tokenlab_challenge.presentation.vo.MovieDetailsVO

class CatalogMoviesAdapter(
    private val itemClick: (Long) -> Unit
) : ListAdapter<MovieDetailsVO, CatalogMoviesAdapter.MovieViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent.inflate(R.layout.item_list_movies))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MovieDetailsVO>() {
            override fun areContentsTheSame(oldItem: MovieDetailsVO, newItem: MovieDetailsVO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: MovieDetailsVO, newItem: MovieDetailsVO): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindTo(movieResponse: MovieDetailsVO) {
            with(movieResponse) {
                itemView.poster.loadFromUrl(posterUrl)
                itemView.title.text = title
                itemView.date.text = releaseDate
                itemView.rating.text = voteAverage
                itemView.setOnClickListener { itemClick.invoke(id) }
            }
        }
    }

}