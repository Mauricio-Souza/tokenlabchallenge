package msousa.dev.tokenlab_challenge.domain

import android.content.Context
import msousa.dev.tokenlab_challenge.data.entities.FullMovieDataEntity
import msousa.dev.tokenlab_challenge.data.internal.Utils
import msousa.dev.tokenlab_challenge.data.model.FullMovieDataProps
import msousa.dev.tokenlab_challenge.data.repositories.MoviesRepository

class GetMovieUseCase(
    private val context: Context,
    private val repository: MoviesRepository
) : UseCase<String, FullMovieDataProps>() {

    override suspend fun execute(parameters: String): FullMovieDataProps {
        return if (Utils.isOffline(context)) {
            repository.getMovieByIdFromDB(parameters.toLong())!!
        } else {
            val movie = repository.getMovieById(parameters)
            repository.insertMovie(movie)
            return movie
        }
    }
}