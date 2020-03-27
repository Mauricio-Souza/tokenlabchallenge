package msousa.dev.tokenlab_challenge.presentation.common.observers

import androidx.lifecycle.Observer
import msousa.dev.tokenlab_challenge.domain.result.Result
import java.lang.Exception

class ErrorObserver<T>(
    private val consumer: (Exception) -> Unit
) : Observer<Result<T>> {

    override fun onChanged(exceptionResult: Result<T>?) {
        exceptionResult?.let { result ->
            if (result is Result.Error) consumer.invoke(result.exception)
        }
    }
}