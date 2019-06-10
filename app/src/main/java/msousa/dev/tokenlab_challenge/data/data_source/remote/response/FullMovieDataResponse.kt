package msousa.dev.tokenlab_challenge.data.data_source.remote.response

import com.google.gson.annotations.SerializedName
import msousa.dev.tokenlab_challenge.data.model.FullMovieDataProps
import msousa.dev.tokenlab_challenge.data.model.MovieCollection
import msousa.dev.tokenlab_challenge.data.model.ProductionCompany
import msousa.dev.tokenlab_challenge.data.model.ProductionCountry

data class FullMovieDataResponse (
    override val adult: Boolean,
    @SerializedName("backdrop_url")
    override val backdropUrl: String,
    @SerializedName("belongs_to_collection")
    val collection: MovieCollection,
    val budget: Int,
    override val genres: List<String>,
    override val id: Long,
    @SerializedName("imdb_id")
    val imdbId: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    override val overview: String,
    override val popularity: Float,
    @SerializedName("poster_url")
    override val posterUrl: String,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("spoken_languages")
    val spoken_languages: List<ProductionCountry>,
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
) : FullMovieDataProps