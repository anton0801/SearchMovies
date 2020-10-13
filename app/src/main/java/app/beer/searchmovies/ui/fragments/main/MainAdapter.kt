package app.beer.searchmovies.ui.fragments.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.beer.searchmovies.R
import app.beer.searchmovies.models.MovieItem
import app.beer.searchmovies.utils.downloadAndSetImage
import kotlinx.android.synthetic.main.movie_item.view.*

class MainAdapter(val movies: List<MovieItem>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var listener: OnItemListener? = null

    fun setListener(onItemListener: OnItemListener) {
        listener = onItemListener
    }

    fun getListener(): OnItemListener {
        return listener!!
    }

    interface OnItemListener {
        fun onItemClick(position: Int)

        fun onItemLongClick(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieItem: MovieItem, position: Int) {
            itemView.movie_image.downloadAndSetImage(movieItem.movieJSON.poster_path)
            itemView.movie_name.text = movieItem.movieJSON.title
            itemView.movie_description.text = movieItem.movieJSON.overview

            itemView.add_to_favorite_btn.setOnClickListener {
                listener?.onItemClick(position)
            }
            itemView.setOnLongClickListener {
                listener?.onItemLongClick(position)
                true
            }
            itemView.setOnClickListener { }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, position)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}