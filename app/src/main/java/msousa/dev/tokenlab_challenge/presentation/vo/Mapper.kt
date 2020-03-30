package msousa.dev.tokenlab_challenge.presentation.vo

import msousa.dev.tokenlab_challenge.domain.dto.MovieDataDto
import msousa.dev.tokenlab_challenge.domain.dto.MovieDetailsDto
import msousa.dev.tokenlab_challenge.domain.dto.MoviesListDto
import msousa.dev.tokenlab_challenge.presentation.extesions.formattedDate

fun MovieDataDto.toVO() = MovieDataVO(
    id = id,
    adult = adult,
    backdropUrl = backdropUrl,
    genres = genres.toGenresText().toString(),
    title = title,
    tagline = tagline,
    overview = overview,
    popularity = popularity.toString(),
    posterUrl = posterUrl,
    voteAverage = voteAverage,
    voteCount = voteCount,
    runtime = "$runtime min.",
    releaseDate = releaseDate.formattedDate(),
    status = status
)

fun MovieDetailsDto.toVO() = MovieDetailsVO(
    id = id,
    voteAverage = voteAverage.toString(),
    title = title,
    posterUrl = posterUrl,
    releaseDate = releaseDate.formattedDate()
)

fun MoviesListDto.toVO() = MoviesVO(
    list = list?.mapVO()
)

fun List<MovieDetailsDto>?.mapVO() = this?.map { it.toVO() }

fun List<String>.toGenresText() = StringBuilder().apply {
    forEach { text ->
        append(text)
        append(", ")
    }
    removeRange(IntRange((length-2), length.dec()))
}