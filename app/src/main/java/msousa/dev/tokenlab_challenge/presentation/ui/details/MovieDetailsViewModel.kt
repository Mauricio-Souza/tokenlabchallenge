package msousa.dev.tokenlab_challenge.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import msousa.dev.tokenlab_challenge.data.internal.*
import msousa.dev.tokenlab_challenge.domain.dto.MovieDataDto
import msousa.dev.tokenlab_challenge.domain.usecases.GetMovieUseCase
import msousa.dev.tokenlab_challenge.domain.result.Result
import msousa.dev.tokenlab_challenge.presentation.common.Event
import msousa.dev.tokenlab_challenge.presentation.ui.BaseViewModel
import msousa.dev.tokenlab_challenge.presentation.common.observers.ErrorObserver
import msousa.dev.tokenlab_challenge.presentation.common.observers.SuccessObserver
import msousa.dev.tokenlab_challenge.presentation.vo.MovieDataVO
import msousa.dev.tokenlab_challenge.presentation.vo.toVO
import java.lang.Exception
import java.lang.NullPointerException

class MovieDetailsViewModel(
    private val getMovieUseCase: GetMovieUseCase
) : BaseViewModel() {

    private val movieDetailsResult = MediatorLiveData<Result<MovieDataDto?>>()
    private val movieDetails = MediatorLiveData<MovieDataVO>()
    private val movieDetailsNotFound = MediatorLiveData<Event<Unit>>()
    private val movieNotFoundInCache = MediatorLiveData<Event<Unit>>()

    init {
        isLoading(movieDetailsResult)
        isServerError(movieDetailsResult)
        isOffline(movieDetailsResult)

        movieDetails.addSource(movieDetailsResult, SuccessObserver { movie ->
                movieDetails.value = movie?.toVO()
            })

        movieNotFoundInCache.addSource(movieDetailsResult,
            ErrorObserver { err ->
                if (err is KotlinNullPointerException) movieNotFoundInCache.value =
                    Event(Unit)
            })

        movieDetailsNotFound.addSource(movieDetailsResult,
            ErrorObserver { err ->
                if (err is MovieNotFoundException) movieDetailsNotFound.value =
                    Event(Unit)
            })
    }

    fun fetchMovieDetails(movieId: String) {
        movieDetailsResult.addSource(getMovieUseCase.invoke(movieId)) { r -> movieDetailsResult.value = r }
    }

    fun getMovieDetails() = movieDetails as LiveData<MovieDataVO?>

    fun movieDetailsNotFound() = movieDetailsNotFound as LiveData<Event<Unit>>

    fun movieNotFoundInCache() = movieNotFoundInCache as LiveData<Event<Unit>>
}