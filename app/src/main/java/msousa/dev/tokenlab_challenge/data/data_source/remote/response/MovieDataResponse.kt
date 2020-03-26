package msousa.dev.tokenlab_challenge.data.data_source.remote.response

import com.google.gson.annotations.SerializedName
import msousa.dev.tokenlab_challenge.data.model.IMovieData

data class MovieDataResponse (
    override val adult: Boolean,
    @SerializedName("backdrop_url")
    override val backdropUrl: String,
    override val genres: List<String>,
    override val id: Long,
    override val overview: String,
    override val popularity: Float,
    @SerializedName("poster_url")
    override val posterUrl: String,
    override val status: String,
    override val tagline: String,
    override val title: String,
    @SerializedName("vote_average")
    override val voteAverage: Double,
    @SerializedName("vote_count")
    override val voteCount: Int,
    override val runtime: Int,
    @SerializedName("release_date")
    override val releaseDate: String
) : IMovieData