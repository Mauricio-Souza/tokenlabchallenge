package msousa.dev.tokenlab_challenge.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetails (
    @SerializedName("id")
    override val id: Long,
    @SerializedName("vote_average")
    override val voteAverage: Float,
    override val title: String,
    @SerializedName("poster_url")
    override val posterUrl: String,
    @SerializedName("release_date")
    override val releaseDate: String
) : IMovieDetails