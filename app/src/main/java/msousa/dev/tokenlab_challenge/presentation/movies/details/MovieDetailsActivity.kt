package msousa.dev.tokenlab_challenge.presentation.movies.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.item_list_movies.view.*
import kotlinx.android.synthetic.main.movies_details_placeholder_view.*
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.data.model.FullMovieDataProps
import msousa.dev.tokenlab_challenge.formattedDate
import msousa.dev.tokenlab_challenge.presentation.gone
import msousa.dev.tokenlab_challenge.presentation.makeSnackbar
import msousa.dev.tokenlab_challenge.presentation.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

const val MOVIE_ID = "movieId"

class MovieDetailsActivity : AppCompatActivity(), LifecycleOwner {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()

    private lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.currentState.isAtLeast(Lifecycle.State.CREATED)


        val movieID: Long = intent.getLongExtra(MOVIE_ID, 0L)

        movieDetailsViewModel.fetchMovieDetails(movieID.toString())

        movieDetailsViewModel.getMovieDetails().observe(this, Observer { data ->
            data?.let { setMovieDetails(it) }
        })

        movieDetailsViewModel.movieDetailsNotFound().observe(this, Observer { hasError ->
            if (hasError) makeSnackbar(getString(R.string.text_data_not_found))
        })

        movieDetailsViewModel.isServerError().observe(this, Observer { hasError ->
            if (hasError) makeSnackbar(getString(R.string.text_error_occurred_on_the_server))
        })

        movieDetailsViewModel.isLoading().observe(this, Observer { isLoading ->
            if (isLoading) {
                shimmerView.visible()
                shimmerView.startShimmer()
                movieDataLayout.gone()
                stickerView.gone()
            } else {
                shimmerView.gone()
                shimmerView.stopShimmer()
                movieDataLayout.visible()
                stickerView.gone()
            }
        })

        movieDetailsViewModel.movieNotFoundInCache().observe(this, Observer { movieNotFound ->
            if (movieNotFound) {
                stickerView.visible()
                movieDataLayout.gone()
            }
        })

    }

    @SuppressLint("SetTextI18n")
    private fun setMovieDetails(data: FullMovieDataProps) {
        Picasso.get().load(data.posterUrl).into(moviePoster)
        movieName.text = data.title
        movieOverwiew.text = data.overview
        releaseDate.text = data.releaseDate.formattedDate()
        if (data.genres.size > 1) movieGenres.text = "${data.genres[0]}, ${data.genres[1]}"
        else movieGenres.text = data.genres[0]
        movieDuration.text = "${data.runtime} min."
        moviePopularity.text = data.popularity.toString()
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.currentState.isAtLeast(Lifecycle.State.DESTROYED)
    }
}