package msousa.dev.tokenlab_challenge.presentation.vo

data class MovieDataVO (
    val id: Long,
    val adult: Boolean,
    val backdropUrl: String,
    val genres: String,
    val title: String,
    val tagline: String,
    val overview: String,
    val popularity: String,
    val posterUrl: String,
    val voteAverage: Double,
    val voteCount: Int,
    val runtime: String,
    val releaseDate: String,
    val status: String
)