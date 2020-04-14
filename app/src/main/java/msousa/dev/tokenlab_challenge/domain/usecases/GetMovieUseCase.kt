package msousa.dev.tokenlab_challenge.domain.usecases

import android.content.Context
import msousa.dev.tokenlab_challenge.domain.IMoviesRepository
import msousa.dev.tokenlab_challenge.utils.Utils
import msousa.dev.tokenlab_challenge.domain.dto.MovieDataDto

class GetMovieUseCase(
    private val context: Context,
    private val repository: IMoviesRepository
) : UseCase<String, MovieDataDto?>() {

    override suspend fun execute(parameters: String): MovieDataDto? {
        return if (Utils.isOffline(context)) {
            repository.getMovieByIdFromCache(id = parameters.toLong())
        } else {
            val movie = repository.getMovieById(id = parameters)
            repository.insertMovie(movie)
            movie
        }
    }
}