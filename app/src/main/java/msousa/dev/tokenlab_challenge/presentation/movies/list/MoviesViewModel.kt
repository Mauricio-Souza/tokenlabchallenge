package msousa.dev.tokenlab_challenge.presentation.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import msousa.dev.tokenlab_challenge.data.dto.PartialMovieDataDto
import msousa.dev.tokenlab_challenge.data.internal.*
import msousa.dev.tokenlab_challenge.domain.GetListMoviesUseCase
import msousa.dev.tokenlab_challenge.presentation.movies.BaseViewModel

class MoviesViewModel(
    private val getListMoviesUseCase: GetListMoviesUseCase
) : BaseViewModel() {

    private val moviesListResult = MediatorLiveData<Result<PartialMovieDataDto>>()
    private val moviesList = MediatorLiveData<PartialMovieDataDto>()
    private val moviesNotFound = MediatorLiveData<Boolean>()

    init {
        isLoading(moviesListResult)
        isServerError(moviesListResult)
        isOffline(moviesListResult)

        moviesNotFound.addSource(moviesListResult, ErrorObserver { err ->
            moviesNotFound.value = err is MovieNotFoundException
        })

        moviesList.addSource(moviesListResult, SuccessObserver { movies ->
            moviesList.value = movies
        })

    }

    fun getMoviesList() {
        moviesListResult.addSource(getListMoviesUseCase.invoke(Unit)) { r ->
            moviesListResult.value = r
        }
    }

    fun movieNotFound() = moviesNotFound as LiveData<Boolean>
    fun moviesList() = moviesList as LiveData<PartialMovieDataDto>
}