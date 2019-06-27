package msousa.dev.tokenlab_challenge.presentation.movies.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.presentation.*
import msousa.dev.tokenlab_challenge.presentation.movies.details.MOVIE_ID
import msousa.dev.tokenlab_challenge.presentation.movies.details.MovieDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private val moviesViewModel: MoviesViewModel by viewModel()

    private lateinit var adapter: CatologMoviesAdapter
    private lateinit var lifecycleRegistry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.currentState.isAtLeast(Lifecycle.State.CREATED)

        moviesViewModel.getMoviesList()

        moviesViewModel.moviesList().observe(this, Observer { movies ->
            adapter.submitList(movies.movieList)
        })

        moviesViewModel.movieNotFound().observe(this, Observer { notFound ->
            if (notFound) makeSnackbar(getString(R.string.text_movies_not_found))
        })

        moviesViewModel.isServerError().observe(this, Observer { err ->
            if (err) makeSnackbar(getString(R.string.text_error_occurred_on_the_server))
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

        adapter = CatologMoviesAdapter { movieId ->
            launchActivity<MovieDetailsActivity> {
                putExtra(MOVIE_ID, movieId)
            }
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerMovies.layoutManager = LinearLayoutManager(this)
        recyclerMovies.adapter = adapter
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.currentState.isAtLeast(Lifecycle.State.DESTROYED)
    }
}
