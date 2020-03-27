package msousa.dev.tokenlab_challenge.presentation.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.presentation.common.observers.EventObserver
import msousa.dev.tokenlab_challenge.presentation.extesions.gone
import msousa.dev.tokenlab_challenge.presentation.extesions.launchActivity
import msousa.dev.tokenlab_challenge.presentation.extesions.showSnackbar
import msousa.dev.tokenlab_challenge.presentation.extesions.visible
import msousa.dev.tokenlab_challenge.presentation.ui.CatologMoviesAdapter
import msousa.dev.tokenlab_challenge.presentation.ui.details.MOVIE_ID
import msousa.dev.tokenlab_challenge.presentation.ui.details.MovieDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val moviesViewModel: MoviesViewModel by viewModel()

    private lateinit var moviesAdapter: CatologMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesViewModel.getMoviesList()

        moviesViewModel.moviesList().observe(this, Observer { movies ->
            moviesAdapter.submitList(movies.list)
        })

        moviesViewModel.moviesNotFound().observe(this,
            EventObserver {
                showSnackbar(getString(R.string.text_movies_not_found))
            })

        moviesViewModel.isServerError().observe(this,
            EventObserver {
                showSnackbar(getString(R.string.text_error_occurred_on_the_server))
            })

        moviesViewModel.isLoading().observe(this, Observer { loading ->
            if (loading != null && loading) {
                shimmerView.visible()
                shimmerView.startShimmer()
            } else {
                shimmerView.stopShimmer()
                shimmerView.gone()
            }
        })

        moviesAdapter =
            CatologMoviesAdapter { movieId ->
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
