package app.beer.searchmovies.ui.objects

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import app.beer.searchmovies.R
import app.beer.searchmovies.utils.APP_ACTIVITY
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader

class AppDrawer(val context: Context) {

    private lateinit var drawer: Drawer

    private lateinit var accountHeader: AccountHeader
    private lateinit var currentProfile: ProfileDrawerItem

    fun create(toolbar: Toolbar) {
        createHeader()
        createDrawer(toolbar)
    }

    private fun createHeader() {
        currentProfile = ProfileDrawerItem()
            .withName("User name")
            .withEmail("user_email@gmail.com")
            .withIcon(APP_ACTIVITY.getDrawable(R.drawable.ic_launcher_background))

        accountHeader = AccountHeaderBuilder()
            .withActivity(APP_ACTIVITY)
            .addProfiles(currentProfile)
            .build()
    }

    private fun createDrawer(toolbar: Toolbar) {
        drawer = DrawerBuilder()
            .withActivity(APP_ACTIVITY)
            .withAccountHeader(accountHeader)
            .withToolbar(toolbar)
            .withDisplayBelowStatusBar(true) // чтобы drawer выходил из под toolbar не перекрывая его
            .withActionBarDrawerToggleAnimated(true) // включаем анимирование верхнего button который говорит что наш drawer открыт или закрыт
            .addDrawerItems(
                PrimaryDrawerItem()
                    .withIdentifier(100)
                    .withName(R.string.home_label)
                    .withIcon(R.drawable.ic_home),
                PrimaryDrawerItem()
                    .withName("Категорий")
                    .withIdentifier(101),
                DividerDrawerItem(),
                PrimaryDrawerItem()
                    .withIdentifier(102)
                    .withName(R.string.about_label)
                    .withIcon(R.drawable.ic_about),
                DividerDrawerItem(),
                SecondaryDrawerItem()
                    .withName(context.getString(R.string.exit_label))
                    .withIcon(R.drawable.ic_exit)
                    .withIdentifier(103)
            )
            .withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    when (drawerItem.identifier) {
                        100L -> {
                            Toast.makeText(context, "Home", Toast.LENGTH_SHORT).show()
                        }
                        101L -> {
                            Toast.makeText(context, "About", Toast.LENGTH_SHORT).show()
                        }
                        102L -> {
                            val dialog = AlertDialog.Builder(context)
                                .setTitle(context.getString(R.string.exit_from_account_dialog_title))
                                .setMessage(context.getString(R.string.exit_from_account_dialog_message))
                                .setPositiveButton(context.getString(R.string.yes_label)) { dialog, which ->
                                    // сделать выход из аккаунта
                                    dialog.dismiss()
                                    drawer.closeDrawer()
                                }
                                .setNegativeButton(context.getString(R.string.no_label)) { dialog, which ->
                                    dialog.dismiss()
                                }
                                .setCancelable(false)
                                .create()
                            dialog.show()

                        }
                    }
                    return true
                }

            })
            .build()
    }

    fun getDrawer(): Drawer {
        return drawer
    }

    fun enableDrawer() {
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        drawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        drawer.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        APP_ACTIVITY.toolbar.setNavigationOnClickListener {
            drawer.openDrawer()
        }
    }

    fun disableDrawer() {
        drawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawer.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        APP_ACTIVITY.toolbar.setNavigationOnClickListener {
            APP_ACTIVITY.supportFragmentManager.popBackStack()
        }
    }

//    private fun initLoader() {
//        DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
//            override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable) {
//                if (USER.photoUrl.equals("empty")) {
//                    imageView.setImageResource(R.drawable.default_photo)
//                } else {
//                    imageView.downloadAndSetImage(uri.toString())
//                }
//            }
//        })
//    }

}