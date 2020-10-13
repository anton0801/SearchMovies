package app.beer.searchmovies.utils

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "RETROFIT_ERROR"

class AppCallbackRetrofit<T>(val progressBar: ProgressBar?, val onSuccess: (call: Call<T>, response: Response<T>) -> Unit) : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.d(TAG, "onFailure: ${t.message}")
        showToast("Что то пошло не так")
        if (progressBar != null) {
            progressBar.visibility = View.GONE
        }
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onSuccess(call, response)
    }


}