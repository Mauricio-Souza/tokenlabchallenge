package msousa.dev.tokenlab_challenge.data.data_source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDataResponse (
    val adult: Boolean,
    @SerializedName("backdrop_url")
    val backdropUrl: String,
    val genres: List<String>,
    val id: Long,
    val overview: String,
    val popularity: Float,
    @SerializedName("poster_url")
    val posterUrl: String,
    val status: String,
    val tagline: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val runtime: Int,
    @SerializedName("release_date")
    val releaseDate: String
)