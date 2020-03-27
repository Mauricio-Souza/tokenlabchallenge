package msousa.dev.tokenlab_challenge.data.data_source.remote

import msousa.dev.tokenlab_challenge.data.data_source.remote.response.MovieDataResponse
import msousa.dev.tokenlab_challenge.data.data_source.remote.response.MovieDetailsResponse
import msousa.dev.tokenlab_challenge.domain.dto.MovieDataDto
import msousa.dev.tokenlab_challenge.domain.dto.MovieDetailsDto
import msousa.dev.tokenlab_challenge.domain.dto.MoviesListDto

fun List<MovieDetailsResponse>.map() = MoviesListDto(
    list = map { it.toDto() }
)

fun MovieDetailsResponse.toDto() = MovieDetailsDto(
    id = id,
    voteAverage = voteAverage,
    title = title,
    posterUrl = posterUrl,
    releaseDate = releaseDate
)

fun MovieDataResponse.toDto() = MovieDataDto(
    adult = adult,
    backdropUrl = backdropUrl,
    genres = genres,
    id = id,
    overview = overview,
    popularity = popularity,
    posterUrl = posterUrl,
    status = status,
    tagline = tagline,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount,
    runtime = runtime,
    releaseDate = releaseDate
)