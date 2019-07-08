package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.Menu
import android.view.View

import com.example.calculator.UI.CalcFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CalcFragment.OnOperationComplited {

    private var inputFrag: CalcFragment = CalcFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(fragmentView.id, inputFrag).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onMenuOpened(featureId: Int, menu: Menu?): Boolean {
        println("menu opened")
        return true
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
    fun onClear( view: View) {
        inputFrag.onClear(view)
    }

    /**
     * обработка переключения на ввод чисел с плавающей точкой
     */
    fun onIntDoubleSwitch(view: View) {
        inputFrag.onIntDoubleSwitch(view)
    }
}

