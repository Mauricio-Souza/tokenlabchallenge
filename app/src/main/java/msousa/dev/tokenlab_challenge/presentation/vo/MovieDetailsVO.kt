package msousa.dev.tokenlab_challenge.presentation.vo

data class MovieDetailsVO (
    val id: Long,
    val voteAverage: String,
    val title: String,
    val posterUrl: String,
    val releaseDate: String
)