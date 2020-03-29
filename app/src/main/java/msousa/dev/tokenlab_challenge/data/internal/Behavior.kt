package msousa.dev.tokenlab_challenge.data.internal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

data class Behavior<T> (
    val job: Job = SupervisorJob(),
    val scope: CoroutineScope,
    val context: CoroutineContext,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    val execute: (() -> T)? = null,
    val loadingSource: MutableLiveData<Boolean>? = null,
    val successAction: ((T) -> Unit)? = null,
    val errorAction: ((Throwable) -> Unit)? = null,
    val finallyAction: (() -> Unit)? = null
) {

    init {
        loadingSource?.value = true
        try {
            scope.launch(dispatcher) {
                execute?.invoke()?.let {
                    successAction?.invoke(it)
                }
            }
        } catch (e: Exception) {
            errorAction?.invoke(e)
        } finally {
            loadingSource?.postValue(false)
            finallyAction?.invoke()
        }
    }
}
