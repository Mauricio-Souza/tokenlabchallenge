package msousa.dev.tokenlab_challenge.presentation.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.presentation.common.observers.EventObserver
import msousa.dev.tokenlab_challenge.presentation.extensions.*
import msousa.dev.tokenlab_challenge.presentation.ui.CatalogMoviesAdapter
import msousa.dev.tokenlab_challenge.presentation.ui.details.MOVIE_ID
import msousa.dev.tokenlab_challenge.presentation.ui.details.MovieDetailsActivity
import msousa.dev.tokenlab_challenge.presentation.vo.toVO
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel by viewModel()

    private lateinit var moviesAdapter: CatalogMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchMoviesList()

        viewModel.movies().observe(this, Observer { movies ->
            moviesAdapter.submitList(movies.list)
        })

        viewModel.moviesNotFound().observe(this, EventObserver {
                showSnackbar(getString(R.string.text_movies_not_found))
            })

        viewModel.isServerError().observe(this, EventObserver {
                showSnackbar(getString(R.string.text_error_occurred_on_the_server))
            })

        viewModel.isLoading().observe(this, Observer { loading ->
            if (loading != null && loading) {
                shimmerView.visible()
                shimmerView.startShimmer()
            } else {
                shimmerView.stopShimmer()
                shimmerView.gone()
            }
        })

        moviesAdapter =
            CatalogMoviesAdapter { movieId ->
                launchActivity<MovieDetailsActivity> {
                    putExtra(MOVIE_ID, movieId)
                }
            }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerMovies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = moviesAdapter
        }
    }
}
