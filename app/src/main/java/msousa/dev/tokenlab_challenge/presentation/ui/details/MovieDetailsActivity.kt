package msousa.dev.tokenlab_challenge.presentation.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.presentation.common.observers.EventObserver
import msousa.dev.tokenlab_challenge.presentation.extensions.gone
import msousa.dev.tokenlab_challenge.presentation.extensions.loadFromUrl
import msousa.dev.tokenlab_challenge.presentation.extensions.showSnackbar
import msousa.dev.tokenlab_challenge.presentation.extensions.visible
import msousa.dev.tokenlab_challenge.presentation.vo.MovieDataVO
import org.koin.androidx.viewmodel.ext.android.viewModel

const val MOVIE_ID = "movieId"

class MovieDetailsActivity : AppCompatActivity() {

    private val viewModel: MovieDetailsViewModel by viewModel()
    private val movieId by lazy { intent.getLongExtra(MOVIE_ID, 0L) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.run {
            fetchMovieDetails(movieId)

            movieDetails.observe(this@MovieDetailsActivity, moviesDetailsObserver)

            movieDetailsNotFound.observe(this@MovieDetailsActivity, movieDetailsNotFoundObserver)

            isServerError.observe(this@MovieDetailsActivity, serverErrorObserver)

            isLoading.observe(this@MovieDetailsActivity, loadingObserver)

            movieNotFoundInCache.observe(this@MovieDetailsActivity, movieNotFoundObserver)
        }
    }

    private val moviesDetailsObserver = Observer<MovieDataVO?> { movieData ->
        movieData?.let {
            moviePoster?.loadFromUrl(it.posterUrl)
            movieName?.text = it.title
            movieOverwiew?.text = it.overview
            releaseDate?.text = it.releaseDate
            movieGenres?.text = it.genres
            movieDuration?.text = it.runtime
            moviePopularity?.text = it.popularity
        }
    }

    private val loadingObserver = Observer<Boolean> { isLoading ->
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
    }

    private val movieNotFoundObserver = EventObserver<Unit> {
        stickerView.visible()
        movieDataLayout.gone()
    }

    private val movieDetailsNotFoundObserver = EventObserver<Int> { message ->
        showSnackbar(getString(message))
    }

    private val serverErrorObserver = EventObserver<Int> { message ->
        showSnackbar(getString(message))
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }
}