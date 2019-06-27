package msousa.dev.tokenlab_challenge.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import msousa.dev.tokenlab_challenge.data.internal.ErrorObserver
import msousa.dev.tokenlab_challenge.data.internal.Result
import msousa.dev.tokenlab_challenge.data.internal.ServerErrorException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    val isOffline = MediatorLiveData<Boolean>()
    val isLoading = MediatorLiveData<Boolean>()
    val isServerError = MediatorLiveData<Boolean>()

    fun <T> isOffline(liveData: LiveData<T>) {
        isOffline.addSource(Transformations.map(liveData) { value ->
            return@map if (value is Result.Error) value.exception is UnknownHostException
            else false
        }) { offline -> isOffline.value = offline }
    }

    fun <T> isLoading(liveData: LiveData<T>) {
        isLoading.addSource(liveData) { r -> isLoading.value = r is Result.Loading }
    }

    fun <T> isServerError(liveData: LiveData<Result<T>>) {
        isServerError.addSource(liveData, ErrorObserver { err ->
            isServerError.value = err is ServerErrorException
        })
    }

    fun isOffline() = isOffline as LiveData<Boolean>
    fun isLoading() = isLoading as LiveData<Boolean>
    fun isServerError() = isServerError as LiveData<Boolean>
}