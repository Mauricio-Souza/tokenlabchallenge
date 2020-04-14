package msousa.dev.tokenlab_challenge.presentation.extensions

import androidx.lifecycle.MediatorLiveData
import msousa.dev.tokenlab_challenge.domain.result.Result
import msousa.dev.tokenlab_challenge.presentation.common.observers.ErrorObserver
import msousa.dev.tokenlab_challenge.presentation.common.observers.LoadingObserver
import msousa.dev.tokenlab_challenge.presentation.common.observers.SuccessObserver

fun <T, R> MediatorLiveData<T>.loading(source: MediatorLiveData<Result<R>>, body: (Boolean) -> Unit) {
    addSource(source, LoadingObserver(body))
}

fun <T, R> MediatorLiveData<T>.failure(source: MediatorLiveData<Result<R>>, body: (Exception) -> Unit) {
    addSource(source, ErrorObserver(body))
}

fun <T, R> MediatorLiveData<T>.success(source: MediatorLiveData<Result<R>>, body: (R?) -> Unit) {
    addSource(source, SuccessObserver(body))
}