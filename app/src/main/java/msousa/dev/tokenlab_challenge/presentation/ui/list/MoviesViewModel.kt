package msousa.dev.tokenlab_challenge.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import msousa.dev.tokenlab_challenge.data.internal.*
import msousa.dev.tokenlab_challenge.domain.usecases.GetListMoviesUseCase
import msousa.dev.tokenlab_challenge.domain.result.Result
import msousa.dev.tokenlab_challenge.domain.dto.MoviesListDto
import msousa.dev.tokenlab_challenge.presentation.common.Event
import msousa.dev.tokenlab_challenge.presentation.ui.BaseViewModel
import msousa.dev.tokenlab_challenge.presentation.common.observers.ErrorObserver
import msousa.dev.tokenlab_challenge.presentation.common.observers.SuccessObserver
import msousa.dev.tokenlab_challenge.presentation.vo.MoviesVO
import msousa.dev.tokenlab_challenge.presentation.vo.toVO

class MoviesViewModel(
    private val getListMoviesUseCase: GetListMoviesUseCase
) : BaseViewModel() {

    private val moviesListResult = MediatorLiveData<Result<MoviesListDto>>()
    private val moviesList = MediatorLiveData<MoviesVO>()
    private val moviesNotFound = MediatorLiveData<Event<Unit>>()

    init {
        isLoading(moviesListResult)
        isServerError(moviesListResult)
        isOffline(moviesListResult)

        moviesNotFound.addSource(moviesListResult,
            ErrorObserver { err ->
                if (err is MovieNotFoundException) moviesNotFound.value =
                    Event(Unit)
            })

        moviesList.addSource(moviesListResult,
            SuccessObserver { movies ->
                moviesList.value = movies.toVO()
            })
    }

    fun getMoviesList() {
        moviesListResult.addSource(getListMoviesUseCase.invoke(Unit)) { r ->
            moviesListResult.value = r
        }
    }

    fun moviesNotFound() = moviesNotFound as LiveData<Event<Unit>>
    fun moviesList() = moviesList as LiveData<MoviesVO>
}