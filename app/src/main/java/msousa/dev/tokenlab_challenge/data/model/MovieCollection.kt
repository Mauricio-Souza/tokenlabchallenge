package msousa.dev.tokenlab_challenge.data.model

import com.google.gson.annotations.SerializedName

data class MovieCollection (
    val id: Int,
    val name: String,
    @SerializedName("poster_url")
    val posterUrl: String,
    @SerializedName("backdrop_url")
    val backdropUrl: String
)