package msousa.dev.tokenlab_challenge.presentation.vo

import msousa.dev.tokenlab_challenge.domain.dto.MovieDataDto
import msousa.dev.tokenlab_challenge.domain.dto.MovieDetailsDto
import msousa.dev.tokenlab_challenge.domain.dto.MoviesListDto

fun MovieDataDto.toVO() = MovieDataVO(
    id = id,
    adult = adult,
    backdropUrl = backdropUrl,
    genres = genres,
    title = title,
    tagline = tagline,
    overview = overview,
    popularity = popularity,
    posterUrl = posterUrl,
    voteAverage = voteAverage,
    voteCount = voteCount,
    runtime = runtime,
    releaseDate = releaseDate,
    status = status
)

fun MovieDetailsDto.toVO() = MovieDetailsVO(
    id = id,
    voteAverage = voteAverage,
    title = title,
    posterUrl = posterUrl,
    releaseDate = releaseDate
)

fun MoviesListDto.toVO() = MoviesVO(
    list = list?.mapVO()
)

fun List<MovieDetailsDto>?.mapVO() = this?.map { it.toVO() }