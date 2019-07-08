package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar

import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.example.calculator.UI.CalcFragment
import kotlinx.android.synthetic.main.content_main_layout.*

class MainActivity : AppCompatActivity(), CalcFragment.OnOperationComplited,
    NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var inputFrag: CalcFragment = CalcFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(fragmentView.id, inputFrag).commit()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }


    override fun onAttachFragment(fragment: Fragment?) {
        if (fragment is CalcFragment)
            fragment.setInputFragmentCallback(this)
    }

    /**
    //     * обработка события вводы цифр
    //     */
    fun onDigitInput(view: View) {
        inputFrag.onDigitInput(view)
    }

    /**
     * обработка выбора операций над числами
     */
    fun onOperatorClicked(view: View) {
        inputFrag.onOperatorClicked(view)
    }

    /**
     * обработка события удаления введённого символа
     */
    fun onEraseClicked(view: View) {
        inputFrag.onEraseClicked(view)
    }

    /**
     * обработка события сброса данных
     */
    fun onClear(view: View) {
        inputFrag.onClear(view)
    }

    /**
     * обработка переключения на ввод чисел с плавающей точкой
     */
    fun onIntDoubleSwitch(view: View) {
        inputFrag.onIntDoubleSwitch(view)
    }
}

