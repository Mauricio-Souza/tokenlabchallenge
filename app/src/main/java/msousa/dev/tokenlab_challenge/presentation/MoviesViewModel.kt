package msousa.dev.tokenlab_challenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import msousa.dev.tokenlab_challenge.data.dto.PartialMovieDataDto
import msousa.dev.tokenlab_challenge.data.internal.*
import msousa.dev.tokenlab_challenge.domain.GetListMoviesUseCase

class MoviesViewModel(
    private val getListMoviesUseCase: GetListMoviesUseCase
) : ViewModel() {

    private val moviesListResult = MediatorLiveData<Result<PartialMovieDataDto>>()
    private val moviesList = MediatorLiveData<PartialMovieDataDto>()
    private val moviesNotFound = MediatorLiveData<Boolean>()
    private val isLoading = MediatorLiveData<Boolean>()
    private val isServerError = MediatorLiveData<Boolean>()

    init {
        isLoading.addSource(moviesListResult) { r -> isLoading.value = r is Result.Loading }

        isServerError.addSource(moviesListResult, ErrorObserver { err ->
            isServerError.value = err is ServerErrorException
        })

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
    fun isLoading() = isLoading as LiveData<Boolean>
    fun isServerError() = isServerError as LiveData<Boolean>
    fun moviesList() = moviesList as LiveData<PartialMovieDataDto>
}