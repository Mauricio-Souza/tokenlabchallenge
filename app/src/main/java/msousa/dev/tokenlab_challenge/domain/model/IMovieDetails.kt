package msousa.dev.tokenlab_challenge.data.model

interface IMovieDetails {
    val id: Long
    val title: String
    val voteAverage: Float
    val posterUrl: String
    val releaseDate: String
}