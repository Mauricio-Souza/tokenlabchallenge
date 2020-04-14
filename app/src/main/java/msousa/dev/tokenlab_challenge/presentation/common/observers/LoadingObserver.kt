package msousa.dev.tokenlab_challenge.presentation.common.observers

import androidx.lifecycle.Observer
import msousa.dev.tokenlab_challenge.domain.result.Result

class LoadingObserver<T>(
    private val consumer: (Boolean) -> Unit
) : Observer<Result<T>> {
    override fun onChanged(result: Result<T>?) {
        result?.let {
            consumer.invoke(it is Result.Loading)
        }
    }
}