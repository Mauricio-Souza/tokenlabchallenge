package msousa.dev.tokenlab_challenge.data.data_source.remote.response

import msousa.dev.tokenlab_challenge.data.model.PartialMovieDataProps

data class MoviesListResponse (
    val movies: List<PartialMovieDataProps>
)