package msousa.dev.tokenlab_challenge.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import msousa.dev.tokenlab_challenge.presentation.common.observers.ErrorObserver
import msousa.dev.tokenlab_challenge.presentation.common.Event
import msousa.dev.tokenlab_challenge.domain.result.Result
import msousa.dev.tokenlab_challenge.data.internal.ServerErrorException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    val isOffline = MediatorLiveData<Boolean>()
    val isLoading = MediatorLiveData<Boolean>()
    val isServerError = MediatorLiveData<Event<Unit>>()

    fun <T> isOffline(liveData: LiveData<T>) {
        isOffline.addSource(Transformations.map(liveData) { value ->
            return@map value is Result.Error && value.exception is UnknownHostException
        }) { offline -> isOffline.value = offline }
    }

    fun <T> isLoading(liveData: LiveData<T>) {
        isLoading.addSource(liveData) { r -> isLoading.value = r is Result.Loading }
    }

    fun <T> isServerError(liveData: LiveData<Result<T>>) {
        isServerError.addSource(liveData,
            ErrorObserver { err ->
                if (err is ServerErrorException) isServerError.value =
                    Event(Unit)
            })
    }

    fun isOffline() = isOffline as LiveData<Boolean>
    fun isLoading() = isLoading as LiveData<Boolean>
    fun isServerError() = isServerError as LiveData<Event<Unit>>
}