package msousa.dev.tokenlab_challenge.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_movies.view.*
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.data.model.PartialMovieDataProps

class CatologMoviesAdapter(
    private val itemClick: (Long) -> Unit
) : ListAdapter<PartialMovieDataProps, CatologMoviesAdapter.MovieViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_movies, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<PartialMovieDataProps>() {
            override fun areContentsTheSame(oldItem: PartialMovieDataProps, newItem: PartialMovieDataProps): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: PartialMovieDataProps, newItem: PartialMovieDataProps): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindTo(movie: PartialMovieDataProps) {
            with(movie) {
                Picasso.get().load(posterUrl).into(itemView.poster)
                itemView.title.text = title
                itemView.date.text = formattedDate(releaseDate)
                itemView.rating.text = voteAverage.toString()
                itemView.setOnClickListener { itemClick.invoke(id) }
            }
        }

        private fun formattedDate(date: String) : String {
            return if (date.isEmpty()) ""
            else {
                val splitDate = date.split("-")
                return "${splitDate[2]}/${splitDate[1]}/${splitDate[0]}"
            }
        }
    }

}