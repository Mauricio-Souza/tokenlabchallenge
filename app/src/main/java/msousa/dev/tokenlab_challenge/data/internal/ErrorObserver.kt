package msousa.dev.tokenlab_challenge.data.internal

import androidx.lifecycle.Observer
import java.lang.Exception

class ErrorObserver<T>(
    private val consumer: (Exception) -> Unit
) : Observer<Result<T>> {

    override fun onChanged(exceptionResult: Result<T>?) {
        if (exceptionResult != null && exceptionResult is Result.Error) {
            val exception = exceptionResult.exception
            consumer.invoke(exception)
        }
    }
}