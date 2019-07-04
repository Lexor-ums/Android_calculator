package com.example.calculator.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.calculator.R
import com.example.calculator.operations.Operator
import com.example.calculator.operations.Solver
import kotlinx.android.synthetic.main.input_fragment_layout.view.*

class InputFragment : Fragment() {
    private var callback: OnOperationComplited? = null
    lateinit var inputField: TextView
    private var solver = Solver()
    private var isNewNumber: Boolean = true
    private var isFloatingPointNumber: Boolean = false
    private var isSolved: Boolean = false

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.input_fragment_layout, container, false)
        inputField = view.dataField
        return view
    }

    /**
    //     * обработка события вводы цифр
    //     */
    fun onDigitInput(view: View) {
        if (isSolved) {
            inputField.text = ""
            isSolved = false
        }
        val button: Button? = view as? Button
        var digit: String = button?.text.toString()
        var number: String = when (isNewNumber) {
            true -> digit
            else -> inputField.text.toString() + digit
        }
        try {
            if (!isFloatingPointNumber)
                inputField.text = number.toLong().toString()
            else
                inputField.text = number.toDouble().toString()
        } catch (e: NumberFormatException) {
        } finally {
            isNewNumber = false
        }

    }

    /**
     * обработка переключения на ввод чисел с плавающей точкой
     */
    fun onIntDoubleSwitch() {
        if (!isFloatingPointNumber) {
            var number: String = inputField.text.toString() + "."
            inputField.text = number
            isFloatingPointNumber = true
        }

    }

    /**
     * обработка события сброса данных
     */
    fun onClear() {
        inputField.text = "0"
        isFloatingPointNumber = false
        isNewNumber = true
    }

    /**
     * обработка события удаления введённого символа
     */
    fun onEraseClicked() {
        if (inputField.text.isEmpty())
            return
        var number: String = inputField.text.toString()
        if (number.get(number.length - 1).equals('.')) {
            isFloatingPointNumber = false

        }
        number = number.take(number.length - 1)
        inputField.text = number
    }

    /**
     * обработка выбора операций над числами
     */
    fun onOperatorClicked(view: View) {
        var number: Number?
        val button: Button? = view as? Button
        try {
            if (isFloatingPointNumber)
                number = inputField.text.toString().toDouble()
            else
                number = inputField.text.toString().toLong()
        } catch (e: Exception) {
            return
        }

        val res = when (view.id) {
            R.id.commandPlus -> solver.addOperator(Operator.Addition, number)
            R.id.commandMinus -> solver.addOperator(Operator.Subtraction, number)
            R.id.commandDivide -> solver.addOperator(Operator.Division, number)
            R.id.commandMultuply -> solver.addOperator(Operator.Multiply, number)
            R.id.commandCalc -> {
                val res: Number? = solver.finish(number)
                if (res != null) {
                    inputField.text = "= $res"
                    callback?.calculationsComplited(number)
                } else
                    return
                isNewNumber = true
                isFloatingPointNumber = false
                isSolved = true
                return
            }
            else -> null
        }
        if (res != null) {
            callback?.showOperationResult(number, res, button?.text.toString())
            inputField.text = "0"
            isNewNumber = true
            isFloatingPointNumber = false
        } else {
            callback?.calculationsStarted(number, button?.text.toString())
            inputField.text = "0"
            isNewNumber = true
            isFloatingPointNumber = false
        }

    }

    fun setInputFragmentCallback(callback: OnOperationComplited){
        this.callback = callback
    }
    interface OnOperationComplited {
        fun showOperationResult(value: Number, result: Number, operationType: String) {

        }

        fun calculationsComplited(value: Number) {

        }

        fun calculationsStarted(value: Number, operationType: String) {

        }
    }
}

