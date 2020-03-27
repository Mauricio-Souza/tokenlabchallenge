package msousa.dev.tokenlab_challenge.domain

import msousa.dev.tokenlab_challenge.data.data_source.local.entities.MovieEntity
import msousa.dev.tokenlab_challenge.domain.dto.MovieDataDto
import msousa.dev.tokenlab_challenge.domain.dto.MovieDetailsDto
import msousa.dev.tokenlab_challenge.domain.dto.MoviesListDto

interface IMoviesRepository {
    suspend fun getMovies() : MoviesListDto
    suspend fun getMovieById(id: String): MovieDataDto
    fun insertMoviesList(moviesDetails: List<MovieDetailsDto>)
    fun insertMovie(movie: MovieDataDto)
    fun getMoviesFromCache(): MoviesListDto
    fun getMovieByIdFromCache(id: Long): MovieDataDto?
}