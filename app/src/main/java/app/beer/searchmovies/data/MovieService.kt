package app.beer.searchmovies.data

import app.beer.searchmovies.models.MovieItem
import app.beer.searchmovies.models.MovieJSON
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovies(): Call<MovieItem>

    @GET("movie/{movie_id}")
    fun getMovieById(@Path("movie_id") id: Int): Call<MovieJSON>

    companion object {
        fun create(retrofit: Retrofit): MovieService {
            return retrofit.create(MovieService::class.java)
        }
    }

}