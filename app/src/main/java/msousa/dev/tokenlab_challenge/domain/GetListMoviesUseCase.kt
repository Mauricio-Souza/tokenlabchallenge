package msousa.dev.tokenlab_challenge.domain

import android.content.Context
import msousa.dev.tokenlab_challenge.data.dto.PartialMovieDataDto
import msousa.dev.tokenlab_challenge.data.internal.Utils
import msousa.dev.tokenlab_challenge.data.model.PartialMovieData
import msousa.dev.tokenlab_challenge.data.repositories.MoviesRepository

class GetListMoviesUseCase(
    private val context: Context,
    private val repository: MoviesRepository
) : UseCase<Unit, PartialMovieDataDto>() {

    @Suppress("UNCHECKED_CAST")
    override suspend fun execute(parameters: Unit): PartialMovieDataDto {
        return if (Utils.isOffline(context)) {
            repository.getMoviesFromDB()
        } else {
            val partialMovieData = repository.getMovies()
            repository.insertMoviesList(partialMovieData.movieList as List<PartialMovieData>)
            return partialMovieData
        }
    }
}