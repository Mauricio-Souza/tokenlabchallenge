package msousa.dev.tokenlab_challenge.domain.usecases

import android.content.Context
import android.net.ConnectivityManager
import msousa.dev.tokenlab_challenge.data.repositories.MoviesRepository
import msousa.dev.tokenlab_challenge.utils.Utils
import msousa.dev.tokenlab_challenge.domain.dto.MoviesListDto

class GetListMoviesUseCase(
    private val context: Context,
    private val repository: MoviesRepository
) : UseCase<Unit, MoviesListDto>() {

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