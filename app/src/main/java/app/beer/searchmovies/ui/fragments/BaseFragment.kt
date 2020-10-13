package app.beer.searchmovies.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment(resId: Int) : Fragment(resId) {

    override fun onStart() {
        super.onStart()
        retainInstance = true
    }

}