package msousa.dev.tokenlab_challenge.presentation.ui.details

import androidx.lifecycle.MediatorLiveData
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.data.internal.*
import msousa.dev.tokenlab_challenge.domain.dto.MovieDataDto
import msousa.dev.tokenlab_challenge.domain.usecases.GetMovieUseCase
import msousa.dev.tokenlab_challenge.domain.result.Result
import msousa.dev.tokenlab_challenge.presentation.common.Event
import msousa.dev.tokenlab_challenge.presentation.ui.BaseViewModel
import msousa.dev.tokenlab_challenge.presentation.extensions.failure
import msousa.dev.tokenlab_challenge.presentation.extensions.success
import msousa.dev.tokenlab_challenge.presentation.vo.MovieDataVO
import msousa.dev.tokenlab_challenge.presentation.vo.toVO

class MovieDetailsViewModel(
    private val getMovieUseCase: GetMovieUseCase
) : BaseViewModel() {

    private val movieDetailsResult = MediatorLiveData<Result<MovieDataDto?>>()
    val movieDetails = MediatorLiveData<MovieDataVO?>()
    val movieDetailsNotFound = MediatorLiveData<Event<Int>>()
    val movieNotFoundInCache = MediatorLiveData<Event<Unit>>()

    init {
        isLoading(movieDetailsResult)
        isServerError(movieDetailsResult)
        isOffline(movieDetailsResult)

        movieDetails.success(movieDetailsResult) { movie ->
            movieDetails.value = movie?.toVO()
        }

        movieNotFoundInCache.failure(movieDetailsResult) { err ->
            if (err is KotlinNullPointerException)
                movieNotFoundInCache.value = Event(Unit)
        }

        movieDetailsNotFound.failure(movieDetailsResult) { err ->
            if (err is MovieNotFoundException)
                movieDetailsNotFound.value = Event(R.string.text_data_not_found)
        }
    }

    fun fetchMovieDetails(movieId: Long) {
        movieDetailsResult.addSource(getMovieUseCase.invoke(movieId.toString())) { result ->
            movieDetailsResult.value = result
        }
    }
}