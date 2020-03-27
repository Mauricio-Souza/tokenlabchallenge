package msousa.dev.tokenlab_challenge.domain.dto

data class MovieDetailsDto (
    val id: Long,
    val voteAverage: Float,
    val title: String,
    val posterUrl: String,
    val releaseDate: String
)