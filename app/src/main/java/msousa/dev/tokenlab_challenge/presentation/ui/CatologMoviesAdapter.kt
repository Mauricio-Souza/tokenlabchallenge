package msousa.dev.tokenlab_challenge.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_movies.view.*
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.presentation.extesions.formattedDate
import msousa.dev.tokenlab_challenge.presentation.vo.MovieDetailsVO

class CatologMoviesAdapter(
    private val itemClick: (Long) -> Unit
) : ListAdapter<MovieDetailsVO, CatologMoviesAdapter.MovieViewHolder>(
    diffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_movies, parent, false)
        return MovieViewHolder(view)
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
                Picasso.get().load(posterUrl).into(itemView.poster)
                itemView.title.text = title
                itemView.date.text = releaseDate.formattedDate()
                itemView.rating.text = voteAverage.toString()
                itemView.setOnClickListener { itemClick.invoke(id) }
            }
        }
    }

}