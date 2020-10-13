package app.beer.searchmovies.ui.fragments.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.beer.searchmovies.R
import app.beer.searchmovies.data.App
import app.beer.searchmovies.models.MovieItem
import app.beer.searchmovies.models.MovieJSON
import app.beer.searchmovies.ui.fragments.BaseFragment
import app.beer.searchmovies.utils.APP
import app.beer.searchmovies.utils.APP_ACTIVITY
import app.beer.searchmovies.utils.AppCallbackRetrofit
import app.beer.searchmovies.utils.showToast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : BaseFragment(R.layout.fragment_main) {

    lateinit var movies: ArrayList<MovieItem>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MainAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    lateinit var progressBar: ProgressBar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        progressBar = progress_bar
        initRecyclerView()
    }

    private fun initRecyclerView() {
        movies = ArrayList()
        getData()
        showToast(movies.toString())
        recyclerView = recycler_view_movies
        adapter = MainAdapter(movies)
        recyclerView.layoutManager = LinearLayoutManager(APP_ACTIVITY)
        recyclerView.adapter = adapter

        swipeRefreshLayout = swipe_refresh_layout_movies
        swipeRefreshLayout.setColorSchemeColors(Color.YELLOW, Color.CYAN)
        swipeRefreshLayout.setOnRefreshListener {
            getData()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getData() {
        progressBar.visibility = View.VISIBLE
        APP.getMovieService().getPopularMovies().enqueue(AppCallbackRetrofit<MovieItem>(progressBar) { call, response ->
            if (response.isSuccessful) {
                val movieItem = response.body()
                if (movieItem != null) {
                    movies.add(MovieItem(movieItem.movieJSON))
                    recyclerView.adapter?.notifyItemRangeInserted(0, movies.size)
                }
            } else {
                if (response.code() == 404) {

                } else if (response.code() == 500) {

                }
            }
            progressBar.visibility = View.GONE
        })
    }

}