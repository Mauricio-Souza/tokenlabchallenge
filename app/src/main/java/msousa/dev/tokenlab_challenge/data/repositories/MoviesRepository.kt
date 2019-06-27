package msousa.dev.tokenlab_challenge.data.repositories

import msousa.dev.tokenlab_challenge.data.data_source.local.dao.FullMovieDataDao
import msousa.dev.tokenlab_challenge.data.data_source.local.dao.PartialMovieDataDao
import msousa.dev.tokenlab_challenge.data.data_source.remote.RetrofitProvider
import msousa.dev.tokenlab_challenge.data.data_source.remote.response.FullMovieDataResponse
import msousa.dev.tokenlab_challenge.data.data_source.remote.response.MoviesListResponse
import msousa.dev.tokenlab_challenge.data.dto.PartialMovieDataDto
import msousa.dev.tokenlab_challenge.data.entities.FullMovieDataEntity
import msousa.dev.tokenlab_challenge.data.entities.PartialMovieDataEntity
import msousa.dev.tokenlab_challenge.data.model.FullMovieDataProps
import msousa.dev.tokenlab_challenge.data.model.PartialMovieData
import msousa.dev.tokenlab_challenge.data.model.PartialMovieDataProps

class MoviesRepository(
    private val fullMovieDataDao: FullMovieDataDao,
    private val partialMovieDataDao: PartialMovieDataDao,
    private val api: MovieApi = RetrofitProvider.get()
) {

    suspend fun getMovies(): PartialMovieDataDto {
        val response = api.fetchMovies().await()
        val result = ResultApiValidator.handleResponse(response)
        return PartialMovieDataDto(result)
    }

    suspend fun getMovieById(id: String): FullMovieDataProps {
        val response = api.fetchMovieById(id).await()
        return ResultApiValidator.handleResponse(response)
    }

    fun insertMoviesList(list: List<PartialMovieData>) {
        list.forEach { movieData ->
            partialMovieDataDao.insertOrUpdate(
                PartialMovieDataEntity(
                    movieData.id,
                    movieData.voteAverage,
                    movieData.title,
                    movieData.posterUrl,
                    movieData.releaseDate
                )
            )
        }
    }

    fun insertMovie(movie: FullMovieDataProps) {
        val entity = FullMovieDataEntity(
            movie.id, movie.adult, movie.backdropUrl,
            movie.genres, movie.title, movie.tagline,
            movie.overview, movie.popularity, movie.posterUrl,
            movie.voteAverage, movie.voteCount, movie.runtime,
            movie.releaseDate, movie.status)

        fullMovieDataDao.insertOrUpdate(entity)
    }

    fun getMoviesFromDB(): PartialMovieDataDto {
        partialMovieDataDao.getAllMovies()?.let { return PartialMovieDataDto(it) }
        return PartialMovieDataDto(emptyList())
    }

    fun getMovieByIdFromDB(id: Long): FullMovieDataProps? {
        return fullMovieDataDao.fetchMovieById(id)
    }
}