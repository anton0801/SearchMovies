package app.beer.searchmovies.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieJSON(
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("title")
    @Expose
    var title: String = "",
    @SerializedName("overview")
    @Expose
    var overview: String = "",
    @SerializedName("poster_path")
    @Expose
    var poster_path: String = "",
    @SerializedName("")
    @Expose
    var movieYear: Int = 2010,
    @SerializedName("production_countries")
    @Expose
    var countries: Array<Any> = arrayOf(),
    @SerializedName("genres")
    @Expose
    var genres: Array<Any> = arrayOf(),
    @SerializedName("runtime")
    @Expose
    var runtime: Int = 0,
    @SerializedName("revenue")
    @Expose
    var revenue: Int = 0,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String = "",
    @SerializedName("budget")
    @Expose
    var budget: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieJSON

        if (id != other.id) return false
        if (title != other.title) return false
        if (overview != other.overview) return false
        if (poster_path != other.poster_path) return false
        if (movieYear != other.movieYear) return false
        if (!countries.contentEquals(other.countries)) return false
        if (genres != other.genres) return false
        if (runtime != other.runtime) return false
        if (revenue != other.revenue) return false
        if (releaseDate != other.releaseDate) return false
        if (budget != other.budget) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + overview.hashCode()
        result = 31 * result + poster_path.hashCode()
        result = 31 * result + movieYear
        result = 31 * result + countries.contentHashCode()
        result = 31 * result + genres.hashCode()
        result = 31 * result + runtime
        result = 31 * result + revenue
        result = 31 * result + releaseDate.hashCode()
        result = (31 * result + budget).toInt()
        return result
    }
}