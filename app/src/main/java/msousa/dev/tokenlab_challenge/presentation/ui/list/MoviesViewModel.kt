package msousa.dev.tokenlab_challenge.presentation.ui.list

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.data.internal.*
import msousa.dev.tokenlab_challenge.domain.usecases.GetListMoviesUseCase
import msousa.dev.tokenlab_challenge.domain.result.Result
import msousa.dev.tokenlab_challenge.domain.dto.MoviesListDto
import msousa.dev.tokenlab_challenge.presentation.common.Event
import msousa.dev.tokenlab_challenge.presentation.ui.BaseViewModel
import msousa.dev.tokenlab_challenge.presentation.extensions.failure
import msousa.dev.tokenlab_challenge.presentation.extensions.loading
import msousa.dev.tokenlab_challenge.presentation.extensions.success
import msousa.dev.tokenlab_challenge.presentation.vo.MoviesVO
import msousa.dev.tokenlab_challenge.presentation.vo.toVO

class MoviesViewModel(
    private val getListMoviesUseCase: GetListMoviesUseCase
) : BaseViewModel() {

    private val lvResult = MediatorLiveData<Result<MoviesListDto>>()
    val lvMovies = MediatorLiveData<MoviesVO?>()
    val lvMoviesNotFound = MediatorLiveData<Event<Int>>()

    init {
        isServerError(lvResult)
        isOffline(lvResult)

        lvMoviesNotFound.failure(lvResult) { err ->
            if (err is MovieNotFoundException) lvMoviesNotFound.value = Event(R.string.text_movies_not_found)
        }

        isLoading.loading(lvResult) { loading -> isLoading.value = loading }

        lvMovies.success(lvResult) { movies -> lvMovies.value = movies?.toVO() }
    }

    fun exm() {
        viewModelScope.launch {
            isLoading.value = true
            try {

            } catch (e: Exception) {

            }
        }
    }

    fun fetchMoviesList() {
        lvResult.addSource(getListMoviesUseCase.invoke(Unit)) { result ->
            lvResult.value = result
        }
    }
}