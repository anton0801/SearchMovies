package app.beer.searchmovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import app.beer.searchmovies.data.App
import app.beer.searchmovies.ui.fragments.main.MainFragment
import app.beer.searchmovies.ui.fragments.movie_detail.MovieDetailFragment
import app.beer.searchmovies.ui.objects.AppDrawer
import app.beer.searchmovies.utils.APP
import app.beer.searchmovies.utils.APP_ACTIVITY
import app.beer.searchmovies.utils.BottomSheet
import app.beer.searchmovies.utils.replaceFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var drawer: AppDrawer
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        APP = App()
        APP_ACTIVITY = this
        toolbar = main_toolbar
        setSupportActionBar(toolbar)
        initDrawer()
        replaceFragment(MovieDetailFragment.newInstance(2762), true)
//        val bottomSheet = BottomSheet()
//        bottomSheet.show(supportFragmentManager, "TAG")
    }

    private fun initDrawer() {
        drawer = AppDrawer(this)
        drawer.create(toolbar)
    }

    override fun onBackPressed() {
        if (drawer.getDrawer().isDrawerOpen) {
            drawer.getDrawer().closeDrawer()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_invite_friend -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text))
                startActivity(Intent.createChooser(intent, getString(R.string.choose_app_label)))
            }
        }
        return true
    }

}