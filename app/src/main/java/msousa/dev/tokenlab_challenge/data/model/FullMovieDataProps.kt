package msousa.dev.tokenlab_challenge.data.model

interface FullMovieDataProps {
    val id: Long
    val adult: Boolean
    val backdropUrl: String
    val genres: List<String>
    val title: String
    val tagline: String
    val overview: String
    val popularity: Float
    val posterUrl: String
    val voteAverage: Double
    val voteCount: Int
    val runtime: Int
    val releaseDate: String
    val status: String
}