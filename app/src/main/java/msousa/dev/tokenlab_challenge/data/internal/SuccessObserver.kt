package msousa.dev.tokenlab_challenge.data.internal

import androidx.lifecycle.Observer

class SuccessObserver<T>(
    private var consumer: (T) -> Unit
) : Observer<Result<T>> {

    override fun onChanged(tResult: Result<T>?) {
        if (tResult != null && tResult is Result.Success ) {
            val result : Result.Success<T> = tResult
            consumer.invoke(result.data)
        }
    }
}