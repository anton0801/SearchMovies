package app.beer.searchmovies.ui.fragments.movie_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import app.beer.searchmovies.R
import app.beer.searchmovies.data.IMAGE_BASE_URL
import app.beer.searchmovies.models.MovieItem
import app.beer.searchmovies.ui.fragments.BaseFragment
import app.beer.searchmovies.utils.APP
import app.beer.searchmovies.utils.APP_ACTIVITY
import app.beer.searchmovies.utils.AppCallbackRetrofit
import app.beer.searchmovies.utils.downloadAndSetImage
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment(val movieId: Int) : BaseFragment(R.layout.fragment_movie_detail) {

    var movie: MovieItem? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovie()
        initFields()
        APP_ACTIVITY.drawer.disableDrawer()
        APP_ACTIVITY.toolbar = movie_detail_toolbar
        APP_ACTIVITY.title = movie?.movieJSON?.title
    }

    private fun initFields() {
        if (movie != null) {
            movie_image.downloadAndSetImage("$IMAGE_BASE_URL${movie!!.movieJSON.poster_path}")
            movie_name.text = movie!!.movieJSON.title
            movie_description_label.text = "Описание фильма ${movie!!.movieJSON.title}"
            movie_description.text = movie!!.movieJSON.overview
            movie_budget.text = "$ ${movie!!.movieJSON.budget}"
        }
    }

    private fun getMovie() {
        APP.getMovieService().getMovieById(movieId)
            .enqueue(AppCallbackRetrofit(null) { call, response ->
                val movieJson = response.body()
                if (movieJson != null) {
                    movie = MovieItem(movieJson)
                }
                if (response.code() == 404) {
                    not_found_movie.visibility = View.VISIBLE
                    app_bar_movie_detail.visibility = View.GONE
                    movie_data.visibility = View.GONE
                } else if (response.code() != 404) {
                    movie_data.visibility = View.VISIBLE
                    app_bar_movie_detail.visibility = View.VISIBLE
                    not_found_movie.visibility = View.GONE
                }
            })
    }

    companion object {
        fun newInstance(id: Int): Fragment {
            return MovieDetailFragment(id)
        }
    }

}