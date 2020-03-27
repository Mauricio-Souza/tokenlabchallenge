package msousa.dev.tokenlab_challenge.data.data_source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse (
    @SerializedName("id")
    val id: Long,
    @SerializedName("vote_average")
    val voteAverage: Float,
    val title: String,
    @SerializedName("poster_url")
    val posterUrl: String,
    @SerializedName("release_date")
    val releaseDate: String
)