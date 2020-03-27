package msousa.dev.tokenlab_challenge.presentation.vo

data class MovieDataVO (
    val id: Long,
    val adult: Boolean,
    val backdropUrl: String,
    val genres: List<String>,
    val title: String,
    val tagline: String,
    val overview: String,
    val popularity: Float,
    val posterUrl: String,
    val voteAverage: Double,
    val voteCount: Int,
    val runtime: Int,
    val releaseDate: String,
    val status: String
)