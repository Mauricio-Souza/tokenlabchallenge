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
import msousa.dev.tokenlab_challenge.presentation.extensions.failure
import msousa.dev.tokenlab_challenge.presentation.extensions.loading
import msousa.dev.tokenlab_challenge.presentation.extensions.success
import msousa.dev.tokenlab_challenge.presentation.vo.MoviesVO
import msousa.dev.tokenlab_challenge.presentation.vo.toVO

class MoviesViewModel(
    private val getListMoviesUseCase: GetListMoviesUseCase
) : BaseViewModel() {

    val result = MediatorLiveData<Result<MoviesListDto>>()
    private val lvMovies = MediatorLiveData<MoviesVO>()
    private val lvMoviesNotFound = MediatorLiveData<Event<Unit>>()

    init {
        isServerError(result)
        isOffline(result)

        lvMoviesNotFound.failure(result) { err ->
            if (err is MovieNotFoundException) lvMoviesNotFound.value = Event(Unit)
        }

        isLoading.loading(result) { loading -> isLoading.value = loading }

        lvMovies.success(result) { movies -> lvMovies.value = movies?.toVO() }

//        lvMoviesNotFound.addSource(result, ErrorObserver { err ->
//            if (err is MovieNotFoundException) lvMoviesNotFound.value = Event(Unit)
//        })
//
//        lvMovies.addSource(result, SuccessObserver { movies ->
//            lvMovies.value = movies.toVO()
//        })
    }

    fun fetchMoviesList() {
        result.addSource(getListMoviesUseCase.invoke(Unit)) { result ->
            this.result.value = result
        }
    }

    fun moviesNotFound() = lvMoviesNotFound as LiveData<Event<Unit>>
    fun movies() = lvMovies as LiveData<MoviesVO>
}