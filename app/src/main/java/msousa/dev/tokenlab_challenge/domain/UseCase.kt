package msousa.dev.tokenlab_challenge.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import msousa.dev.tokenlab_challenge.data.internal.Result

abstract class UseCase<in P, R> : CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    operator fun invoke(parameters: P, result: MutableLiveData<Result<R>>) = launch {
        result.value = Result.Loading
        try {
            withContext(Dispatchers.IO) {
                try {
                    execute(parameters).let { useCaseResult ->
                        result.postValue(Result.Success(useCaseResult))
                    }
                } catch (e: Exception) {
                    result.postValue(Result.Error(e))
                }
            }
        } catch (e: Exception) {
            result.postValue(Result.Error(e))
        }
    }

    operator fun invoke(parameters: P): LiveData<Result<R>> {
        val liveCallback = MutableLiveData<Result<R>>()
        this(parameters, liveCallback)
        return liveCallback
    }

    fun onDestroy() {
        job.cancelChildren()
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}

suspend operator fun <R> UseCase<Unit, R>.invoke(): LiveData<Result<R>> = this(Unit)
suspend operator fun <R> UseCase<Unit, R>.invoke(result: MutableLiveData<Result<R>>) = this(Unit, result)
