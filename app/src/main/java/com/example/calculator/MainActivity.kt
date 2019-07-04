package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculator.UI.InputFragment
import com.example.calculator.UI.ResultFragment
import com.example.calculator.operations.Operator
import com.example.calculator.operations.Solver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.input_fragment_layout.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), InputFragment.OnOperationComplited {

    private lateinit var inputFrag : InputFragment
    private lateinit var resultFrag : ResultFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputFrag = fragment_input as InputFragment
        resultFrag = fragment_result as ResultFragment
    }

    override fun onAttachFragment(fragment: Fragment?) {
        if(fragment is InputFragment)
            fragment.setInputFragmentCallback(this)
    }

    override fun showOperationResult(value: Number, result: Number, operationType: String) {
        resultFrag.showOperationResult(value, result, operationType)
    }

    override fun calculationsComplited(value: Number) {
        resultFrag.calculationsComplited(value)
    }

    override fun calculationsStarted(value: Number, operationType: String) {
        resultFrag.calculationsStarted(value, operationType)
    }

    /**
    //     * обработка события вводы цифр
    //     */
    fun onDigitInput(view: View){
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
    fun onEraseClicked(@Suppress("UNUSED_PARAMETER")view: View){
        inputFrag.onEraseClicked()
    }
    /**
     * обработка события сброса данных
     */
    fun onClear(@Suppress("UNUSED_PARAMETER")view: View){
        inputFrag.onClear()
        resultFrag.onClear()
    }
    /**
     * обработка переключения на ввод чисел с плавающей точкой
     */
    fun onIntDoubleSwitch(@Suppress("UNUSED_PARAMETER")view: View){
        inputFrag.onIntDoubleSwitch()
    }
}
