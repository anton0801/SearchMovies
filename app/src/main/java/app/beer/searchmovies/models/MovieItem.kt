package app.beer.searchmovies.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("results")
    @Expose
    var movieJSON: MovieJSON
)