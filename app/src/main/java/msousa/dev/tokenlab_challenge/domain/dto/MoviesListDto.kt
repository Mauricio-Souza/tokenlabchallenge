package msousa.dev.tokenlab_challenge.domain.dto

data class MoviesListDto(
    val list: List<MovieDetailsDto>? = emptyList()
)