package msousa.dev.tokenlab_challenge.presentation.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.presentation.common.observers.EventObserver
import msousa.dev.tokenlab_challenge.presentation.extesions.formattedDate
import msousa.dev.tokenlab_challenge.presentation.extesions.gone
import msousa.dev.tokenlab_challenge.presentation.extesions.showSnackbar
import msousa.dev.tokenlab_challenge.presentation.extesions.visible
import msousa.dev.tokenlab_challenge.presentation.vo.MovieDataVO
import org.koin.androidx.viewmodel.ext.android.viewModel

const val MOVIE_ID = "movieId"

class MovieDetailsActivity : AppCompatActivity() {

    private val viewModel: MovieDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.getLongExtra(MOVIE_ID, 0L).let {
            viewModel.fetchMovieDetails(it.toString())
        }

        viewModel.getMovieDetails().observe(this, Observer { data ->
            data?.let { setMovieDetails(it) }
        })

        viewModel.movieDetailsNotFound().observe(this,
            EventObserver {
                showSnackbar(getString(R.string.text_data_not_found))
            })

        viewModel.isServerError().observe(this,
            EventObserver {
                showSnackbar(getString(R.string.text_error_occurred_on_the_server))
            })

        viewModel.isLoading().observe(this, Observer { isLoading ->
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

        viewModel.movieNotFoundInCache().observe(this,
            EventObserver {
                stickerView.visible()
                movieDataLayout.gone()
            })
    }

    @SuppressLint("SetTextI18n")
    private fun setMovieDetails(movieData: MovieDataVO) {
        movieData.let {
            Picasso.get().load(it.posterUrl).into(moviePoster)
            movieName.text = it.title
            movieOverwiew.text = it.overview
            releaseDate.text = it.releaseDate
            movieGenres.text = it.genres
            movieDuration.text = it.runtime
            moviePopularity.text = it.popularity
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}