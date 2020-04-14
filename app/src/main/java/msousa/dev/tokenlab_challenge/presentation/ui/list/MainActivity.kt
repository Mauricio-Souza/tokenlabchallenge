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
import msousa.dev.tokenlab_challenge.presentation.vo.MoviesVO
import msousa.dev.tokenlab_challenge.presentation.vo.toVO
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel by viewModel()

    private lateinit var moviesAdapter: CatalogMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.run {
            fetchMoviesList()

            lvMovies.observe(this@MainActivity, moviesObserver)

            lvMoviesNotFound.observe(this@MainActivity, moviesNotFoundObserver)

            isServerError.observe(this@MainActivity, serverErrorObserver)

            isLoading.observe(this@MainActivity, loadingObserver)
        }

        moviesAdapter = CatalogMoviesAdapter { movieId ->
                launchActivity<MovieDetailsActivity> {
                    putExtra(MOVIE_ID, movieId)
                }
            }

        setupRecyclerView()
    }

    private val moviesObserver = Observer<MoviesVO?> { movies ->
        movies?.let { moviesAdapter.submitList(it.list) }
    }

    private val moviesNotFoundObserver = EventObserver<Int> { message ->
        showSnackbar(getString(message))
    }

    private val serverErrorObserver = EventObserver<Int> { message ->
        showSnackbar(getString(message))
    }

    private val loadingObserver = Observer<Boolean> { isLoading ->
        shimmerView?.apply {
            if (isLoading) {
                visible()
                startShimmer()
            } else {
                stopShimmer()
                gone()
            }
        }
    }

    private fun setupRecyclerView() {
        recyclerMovies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = moviesAdapter
        }
    }
}
