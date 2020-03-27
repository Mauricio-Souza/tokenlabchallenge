package msousa.dev.tokenlab_challenge.domain.usecases

import android.content.Context
import msousa.dev.tokenlab_challenge.data.data_source.remote.response.MovieDetailsResponse
import msousa.dev.tokenlab_challenge.data.repositories.MoviesRepository
import msousa.dev.tokenlab_challenge.domain.Utils
import msousa.dev.tokenlab_challenge.domain.dto.MoviesListDto

class GetListMoviesUseCase(
    private val context: Context,
    private val repository: MoviesRepository
) : UseCase<Unit, MoviesListDto>() {

    @Suppress("UNCHECKED_CAST")
    override suspend fun execute(parameters: Unit): MoviesListDto {
        return if (Utils.isOffline(context)) {
            repository.getMoviesFromCache()
        } else {
            val movies = repository.getMovies()
            movies.list?.let { repository.insertMoviesList(it) }
            return movies
        }
    }
}