package com.example.calculator.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.calculator.R
import com.example.calculator.presentation.base.BaseActivity

import com.example.calculator.utils.navigation.fragmentrouter.FragmentRouter
import com.example.calculator.utils.navigation.fragmentrouter.Screens
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main_layout.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityViewModel>(),
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var viewmodel : MainActivityViewModel

    private var fragmentRouter: FragmentRouter = FragmentRouter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentRouter.initRouter(supportFragmentManager, fragmentView.id, ::finishActivity)
        val toolbar: Toolbar = toolbar
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout? = drawer_layout
        val navView: NavigationView? = nav_view
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
        viewmodel.setFragmentRouter(fragmentRouter)
        navView?.setNavigationItemSelectedListener(this)
    }
    private fun finishActivity(){
        finish()
    }
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        viewmodel?.switchFragment(p0.itemId)
        drawer_layout.closeDrawer(GravityCompat.START)
        return false
    }

    override fun getViewModel(): MainActivityViewModel {
        return viewmodel
    }
}

