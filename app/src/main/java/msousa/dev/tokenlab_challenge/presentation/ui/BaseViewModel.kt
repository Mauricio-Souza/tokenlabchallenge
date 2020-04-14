package msousa.dev.tokenlab_challenge.presentation.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import msousa.dev.tokenlab_challenge.R
import msousa.dev.tokenlab_challenge.presentation.common.Event
import msousa.dev.tokenlab_challenge.domain.result.Result
import msousa.dev.tokenlab_challenge.data.internal.ServerErrorException
import msousa.dev.tokenlab_challenge.presentation.extensions.failure
import msousa.dev.tokenlab_challenge.presentation.extensions.loading
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    val isOffline = MediatorLiveData<Event<Unit>>()
    val isLoading = MediatorLiveData<Boolean>()
    val isServerError = MediatorLiveData<Event<Int>>()

    fun <T> isOffline(source: MediatorLiveData<Result<T>>) {
        isOffline.failure(source) { err ->
            if (err is UnknownHostException) isOffline.value = Event(Unit)
        }
    }

    fun <T> isLoading(source: MediatorLiveData<Result<T>>) {
        isLoading.loading(source) { loading -> isLoading.value = loading }
    }

    fun <T> isServerError(source: MediatorLiveData<Result<T>>) {
        isServerError.failure(source) { err ->
            if (err is ServerErrorException)
                isServerError.value = Event(R.string.text_error_occurred_on_the_server)
        }
    }
}