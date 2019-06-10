package msousa.dev.tokenlab_challenge.data.repositories

import msousa.dev.tokenlab_challenge.data.internal.InternalErrorException
import msousa.dev.tokenlab_challenge.data.internal.MovieNotFoundException
import retrofit2.Response

object ResultApiValidator {
    fun <T> handleResponse(response: Response<T>) : T {
        return when(response.code()) {
            200 -> response.body()!!
            404 -> throw MovieNotFoundException()
            else -> throw InternalErrorException()
        }
    }
}