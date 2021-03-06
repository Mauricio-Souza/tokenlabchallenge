package msousa.dev.tokenlab_challenge.presentation.common.observers

import androidx.lifecycle.Observer
import msousa.dev.tokenlab_challenge.domain.result.Result

class SuccessObserver<T>(
    private var consumer: (T) -> Unit
) : Observer<Result<T>> {

    override fun onChanged(result: Result<T>?) {
        result?.let {
            if (it is Result.Success) consumer.invoke(it.data)
        }
    }
}