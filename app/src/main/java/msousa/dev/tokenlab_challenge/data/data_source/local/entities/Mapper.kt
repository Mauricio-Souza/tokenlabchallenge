package msousa.dev.tokenlab_challenge.data.data_source.local.entities

import msousa.dev.tokenlab_challenge.domain.dto.MovieDataDto
import msousa.dev.tokenlab_challenge.domain.dto.MovieDetailsDto
import msousa.dev.tokenlab_challenge.domain.dto.MoviesListDto

fun MovieDataDto.toEntity() = MovieEntity(
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

fun MovieEntity.toDto() = MovieDataDto(
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

fun MovieDetailsEntity.toDto() = MovieDetailsDto(
    id = id,
    voteAverage = voteAverage,
    title = title,
    posterUrl = posterUrl,
    releaseDate = releaseDate
)

fun List<MovieDetailsEntity>.mapDto() = MoviesListDto(
    map { it.toDto() }
)

fun List<MovieDetailsDto>.map() = map {
    MovieDetailsEntity(
        id = it.id,
        voteAverage = it.voteAverage,
        title = it.title,
        posterUrl = it.posterUrl,
        releaseDate = it.releaseDate
    )
}