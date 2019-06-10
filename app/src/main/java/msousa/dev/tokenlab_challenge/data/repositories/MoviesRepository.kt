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
    private val api: MovieApi = RetrofitProvider.get(),
    private val fullMovieDataDao: FullMovieDataDao,
    private val partialMovieDataDao: PartialMovieDataDao
) {

    suspend fun getMovies(): PartialMovieDataDto {
        val response = api.fetchMovies().await()
        val result = ResultApiValidator.handleResponse(response)
        return PartialMovieDataDto(result.movies)
    }

    suspend fun getMovieById(id: String): FullMovieDataProps {
        val response = api.fetchMovieById(id).await()
        return ResultApiValidator.handleResponse(response)
    }

    suspend fun insertMoviesList(list: List<PartialMovieData>) {
        list.forEach { movieData ->
            partialMovieDataDao.insertOrUpdate(
                PartialMovieDataEntity(
                    movieData.id,
                    movieData.voteAverage,
                    movieData.title,
                    movieData.posterUrl
                )
            )
        }
    }

    suspend fun insertMovie(movie: FullMovieDataEntity) {
        fullMovieDataDao.insertOrUpdate(movie)
    }

    suspend fun getMoviesFromDB(): PartialMovieDataDto {
        partialMovieDataDao.getAllMovies()?.let { return PartialMovieDataDto(it) }
        return PartialMovieDataDto(emptyList())
    }

    suspend fun getMovieByIdFromDB(id: Long): FullMovieDataProps? {
        return fullMovieDataDao.fetchMovieById(id)
    }
}