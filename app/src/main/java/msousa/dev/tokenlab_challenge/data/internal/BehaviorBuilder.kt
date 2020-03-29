package msousa.dev.tokenlab_challenge.data.internal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class BehaviorBuilder<T> {
    private var job: Job = SupervisorJob()
    private var scope: CoroutineScope = CoroutineScope(job + Dispatchers.Main)
    private var context: CoroutineContext = scope.coroutineContext
    private var dispatcher: CoroutineDispatcher = Dispatchers.IO
    private lateinit var execute: () -> T
    private var loadingSource: MutableLiveData<Boolean>? = null
    private var successAction: (T) -> Unit = {}
    private var errorAction: (Throwable) -> Unit = {}
    private var finallyAction: () -> Unit = {}

    fun build() = Behavior(
        job = job,
        scope = scope,
        context = context,
        dispatcher = dispatcher,
        execute = execute,
        loadingSource = loadingSource,
        successAction = successAction,
        errorAction = errorAction,
        finallyAction = finallyAction
    )

    infix fun <T> BehaviorBuilder<T>.run(execute: () -> T) {
        this.execute = execute
    }

    infix fun <T> BehaviorBuilder<T>.addJob(job: Job) {
        this.job = job
    }

    infix fun <T> BehaviorBuilder<T>.addScope(scope: CoroutineScope) {
        this.scope = scope
    }

    infix fun <T> BehaviorBuilder<T>.addContext(context: CoroutineContext) {
        this.context = context
    }

    infix fun <T> BehaviorBuilder<T>.addDispatcher(dispatcher: CoroutineDispatcher) {
        this.dispatcher = dispatcher
    }

    infix fun <T> BehaviorBuilder<T>.addLoadingSource(source: MutableLiveData<Boolean>) {
        this.loadingSource = source
    }

    infix fun <T> BehaviorBuilder<T>.then(action: (T) -> Unit) {
        this.successAction = action
    }

    infix fun <T> BehaviorBuilder<T>.catch(action: (Throwable) -> Unit) {
        this.errorAction = action
    }

    infix fun <T> BehaviorBuilder<T>.finally(action: () -> Unit) {
        this.finallyAction = finallyAction
    }
}