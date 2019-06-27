package msousa.dev.tokenlab_challenge.presentation.movies.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import msousa.dev.tokenlab_challenge.data.internal.ErrorObserver
import msousa.dev.tokenlab_challenge.data.internal.MovieNotFoundException
import msousa.dev.tokenlab_challenge.data.internal.Result
import msousa.dev.tokenlab_challenge.data.internal.SuccessObserver
import msousa.dev.tokenlab_challenge.data.model.FullMovieDataProps
import msousa.dev.tokenlab_challenge.domain.GetMovieUseCase
import msousa.dev.tokenlab_challenge.presentation.movies.BaseViewModel

class MovieDetailsViewModel(
    private val getMovieUseCase: GetMovieUseCase
) : BaseViewModel() {

    private val movieDetailsResult = MediatorLiveData<Result<FullMovieDataProps>>()
    private val movieDetails = MediatorLiveData<FullMovieDataProps>()
    private val movieDetailsNotFound = MediatorLiveData<Boolean>()
    private val movieNotFoundInCache = MediatorLiveData<Boolean>()

    init {
        isLoading(movieDetailsResult)
        isServerError(movieDetailsResult)
        isOffline(movieDetailsResult)

        movieDetails.addSource(movieDetailsResult, SuccessObserver { result ->
            movieDetails.value = result
        })

        movieNotFoundInCache.addSource(movieDetailsResult, ErrorObserver { err ->
            movieNotFoundInCache.value = err is KotlinNullPointerException
        })

        movieDetailsNotFound.addSource(movieDetailsResult, ErrorObserver { err ->
            movieDetailsNotFound.value = err is MovieNotFoundException
        })
    }

    fun fetchMovieDetails(movieId: String) {
        movieDetailsResult.addSource(getMovieUseCase.invoke(movieId)) { r -> movieDetailsResult.value = r }
    }

    fun getMovieDetails() = movieDetails as LiveData<FullMovieDataProps>

    fun movieDetailsNotFound() = movieDetailsNotFound as LiveData<Boolean>

    fun movieNotFoundInCache() = movieNotFoundInCache as LiveData<Boolean>

}