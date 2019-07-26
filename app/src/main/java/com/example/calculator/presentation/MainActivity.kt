package com.example.calculator.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.calculator.R

import com.example.calculator.presentation.calcfragment.CalcFragment
import com.example.calculator.presentation.financialfragment.financialmainfragment.FinancialMainFragment
import com.example.calculator.utils.FragmentUtils
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main_layout.*

class MainActivity : DaggerAppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val id = p0.itemId
        if(id == R.id.nav_financial){
            FragmentUtils.replaceFragment(this, FinancialMainFragment.getInstance(),fragmentView.id, true)
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmentUtils.replaceFragment(this, CalcFragment.getInstance(),fragmentView.id, false)
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

        navView?.setNavigationItemSelectedListener(this)
    }
}

