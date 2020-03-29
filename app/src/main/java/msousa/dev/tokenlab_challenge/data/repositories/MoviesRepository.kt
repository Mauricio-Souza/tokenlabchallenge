package msousa.dev.tokenlab_challenge.data.repositories

import msousa.dev.tokenlab_challenge.data.data_source.local.dao.MovieDao
import msousa.dev.tokenlab_challenge.data.data_source.local.dao.MovieDetailsDao
import msousa.dev.tokenlab_challenge.data.data_source.remote.RetrofitProvider
import msousa.dev.tokenlab_challenge.data.data_source.local.entities.map
import msousa.dev.tokenlab_challenge.data.data_source.local.entities.mapDto
import msousa.dev.tokenlab_challenge.data.data_source.local.entities.toDto
import msousa.dev.tokenlab_challenge.data.data_source.local.entities.toEntity
import msousa.dev.tokenlab_challenge.data.data_source.remote.mapDto
import msousa.dev.tokenlab_challenge.data.data_source.remote.toDto
import msousa.dev.tokenlab_challenge.domain.IMoviesRepository
import msousa.dev.tokenlab_challenge.domain.dto.MovieDataDto
import msousa.dev.tokenlab_challenge.domain.dto.MovieDetailsDto
import msousa.dev.tokenlab_challenge.domain.dto.MoviesListDto

class MoviesRepository(
    private val movieDao: MovieDao,
    private val movieDetailsDao: MovieDetailsDao,
    private val api: MovieApi = RetrofitProvider.get()
) : IMoviesRepository {

    override suspend fun getMovies(): MoviesListDto {
        val response = api.fetchMovies().await()
        val result = ResultApiValidator.handleResponse(response)
        return result.mapDto()
    }

    override suspend fun getMovieById(id: String): MovieDataDto {
        val response = api.fetchMovieById(id).await()
        val result = ResultApiValidator.handleResponse(response)
        return result.toDto()
    }

    override fun insertMoviesList(moviesDetails: List<MovieDetailsDto>) =
        movieDetailsDao.insertOrUpdate(* moviesDetails.map().toTypedArray())

    override fun insertMovie(movie: MovieDataDto) = movieDao.insertOrUpdate(movie.toEntity())

    override fun getMoviesFromCache(): MoviesListDto {
        return movieDetailsDao.getAllMovies()?.mapDto() ?: MoviesListDto()
    }

    override fun getMovieByIdFromCache(id: Long) = movieDao.fetchMovieById(id)?.toDto()
}