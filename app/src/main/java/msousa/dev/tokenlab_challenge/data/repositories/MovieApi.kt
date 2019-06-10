package msousa.dev.tokenlab_challenge.data.repositories

import kotlinx.coroutines.Deferred
import msousa.dev.tokenlab_challenge.data.data_source.remote.response.FullMovieDataResponse
import msousa.dev.tokenlab_challenge.data.data_source.remote.response.MoviesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movies")
    fun fetchMovies() : Deferred<Response<MoviesListResponse>>

    @GET("movies/{id}")
    fun fetchMovieById(@Path("id") id: String) : Deferred<Response<FullMovieDataResponse>>
}