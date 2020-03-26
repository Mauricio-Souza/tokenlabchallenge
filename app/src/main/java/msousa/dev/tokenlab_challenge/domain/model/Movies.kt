package msousa.dev.tokenlab_challenge.domain.model

data class MovieDetails(
    override val id: Long,
    override val title: String,
    override val voteAverage: Float,
    override val posterUrl: String,
    override val releaseDate: String
) : IMovieDetails

data class MovieData(
    override val id: Long,
    override val adult: Boolean,
    override val backdropUrl: String,
    override val genres: List<String>,
    override val title: String,
    override val tagline: String,
    override val overview: String,
    override val popularity: Float,
    override val posterUrl: String,
    override val voteAverage: Double,
    override val voteCount: Int,
    override val runtime: Int,
    override val releaseDate: String,
    override val status: String
) : IMovieData

data class Movies(
    val list: List<MovieDetails>
)