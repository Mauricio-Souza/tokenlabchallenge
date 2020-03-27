package msousa.dev.tokenlab_challenge.presentation.common.observers

import androidx.lifecycle.Observer
import msousa.dev.tokenlab_challenge.domain.result.Result

class SuccessObserver<T>(
    private var consumer: (T) -> Unit
) : Observer<Result<T>> {

    override fun onChanged(successResult: Result<T>?) {
        successResult?.let { result ->
            if (result is Result.Success) consumer.invoke(result.data)
        }
    }
}