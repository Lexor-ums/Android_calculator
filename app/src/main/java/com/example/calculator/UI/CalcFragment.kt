package com.example.calculator.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.calculator.R
import com.example.calculator.operations.Operator
import com.example.calculator.operations.Solver
import kotlinx.android.synthetic.main.calc_layout.view.*

class CalcFragment  : Fragment() {
    private var callback: OnOperationComplited? = null
    lateinit var inputField: TextView
    private var solver = Solver()
    private var isNewNumber: Boolean = true
    private var isFloatingPointNumber: Boolean = false
    private var isSolved: Boolean = false
    lateinit var historyTextView: TextView


    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.calc_layout, container, false)
        inputField = view.dataField
        historyTextView = view.historyField
        historyTextView.text = savedInstanceState?.getString("history")
        historyTextView.movementMethod = ScrollingMovementMethod()
        if(savedInstanceState == null){
            inputField.text = "0"
        }
        else{
            inputField.text = savedInstanceState.getString("currentValue")
            isFloatingPointNumber = savedInstanceState.getBoolean("isFloatingPointNumber")
            isNewNumber = savedInstanceState.getBoolean("isNewNumber")
            isSolved = savedInstanceState.getBoolean("isSolved")
        }
        return view
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isNewNumber", isNewNumber)
        outState.putBoolean("isFloatingPointNumber", isFloatingPointNumber)
        outState.putBoolean("isSolved", isSolved)
        outState.putString("currentValue", inputField.text.toString())
    }

    /**
     * добавляет в последнюю строку поля с историей операций
     * значение к символу выполняемой операции
     * @param value числовое занчение
     * @return обновленное содержимое текстового поля
     */
    private fun replaceHistoryText(value : Number){
        var content = historyTextView.text.split("\n").toMutableList()
        if(content.size > historyTextView.maxLines)
            content = content.slice(IntRange(content.lastIndex - historyTextView.maxLines,
                content.lastIndex)).toMutableList()
        val newOperationText = content[content.lastIndex] + value.toString()
        content.removeAt(content.lastIndex)
        content.add(newOperationText)
        historyTextView.text =""
        content.forEach { str ->
            historyTextView.append( str + "\n")
        }
    }

    /**
     * обработак события вводы цифр
     */
    fun onDigitInput(view: View){
        if (isSolved){
            inputField.text = ""
            isSolved = false
        }
        val button : Button? = view as? Button
        var digit: String = button?.text.toString()
        var number: String = when(isNewNumber){
            true -> digit
            else -> inputField.text.toString() + digit
        }
        try {
            if(!isFloatingPointNumber)
                inputField.text = number.toLong().toString()
            else
                inputField.text = number.toDouble().toString()
        }
        catch (e: NumberFormatException){
        }
        finally {
            isNewNumber = false
        }

    }

    /**
     * обработка переключения на ффод чисел с плавающей точкой
     */
    fun onIntDoubleSwitch(@Suppress("UNUSED_PARAMETER")view: View){
        if(!isFloatingPointNumber) {
            var number: String = inputField.text.toString() + "."
            inputField.text = number
            isFloatingPointNumber = true
        }

    }

    /**
     * обработка события сброса данных
     */
    fun onClear(@Suppress("UNUSED_PARAMETER")view: View){
        inputField.text = "0"
        historyTextView.text = ""
        isFloatingPointNumber = false
        isNewNumber = true
    }

    /**
     * обработка события удаления введённого символа
     */
    fun onEraseClicked(@Suppress("UNUSED_PARAMETER")view: View){
        if (inputField.text.isEmpty())
            return
        var number : String = inputField.text.toString()
        if(number.get(number.length - 1).equals('.')) {
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
        val button : Button? = view as? Button
        try {
            if(isFloatingPointNumber)
                number = inputField.text.toString().toDouble()
            else
                number = inputField.text.toString().toLong()
        }
        catch (e : Exception){
            return
        }

        val res = when(view.id){
            R.id.commandPlus -> solver.addOperator(Operator.Addition, number)
            R.id.commandMinus -> solver.addOperator(Operator.Subtraction, number)
            R.id.commandDivide -> solver.addOperator(Operator.Division, number)
            R.id.commandMultuply -> solver.addOperator(Operator.Multiply, number)
            R.id.commandCalc -> {
                val res : Number? = solver.finish(number)
                if(res != null) {
                    inputField.text = "= $res"
                    replaceHistoryText(number)
                }
                else
                    return
                historyTextView.append("--------------------")
                isNewNumber = true
                isFloatingPointNumber = false
                isSolved = true
                return
            }
            else -> null
        }
        if(res != null){
            replaceHistoryText(number)
            historyTextView.append("= $res")
            historyTextView.append("\n" + button?.text.toString())
            inputField.text = "0"
            isNewNumber = true
            isFloatingPointNumber = false
        }
        else{
            historyTextView.append("\n" + number.toString())
            historyTextView.append("\n" + button?.text.toString())
            inputField.text = "0"
            isNewNumber = true
            isFloatingPointNumber = false
        }
    }
    fun setInputFragmentCallback(callback: CalcFragment.OnOperationComplited){
        this.callback = callback
    }
    interface OnOperationComplited {
        /**
         * Оповещение о выполении математичесвкой операции
         * @param value правый операнд
         * @param result результат вычисления
         * @param operationType символ операции
         */
        fun showOperationResult(value: Number, result: Number, operationType: String) {

        }

        /**
         * Оповещение о завершении расчётов
         * @param value конечное значение
         */
        fun calculationsComplited(value: Number) {

        }

        /**
         * Оповещении о начале новой серии вычислений
         * @param value исходное число
         * @param operationType символ операции
         */
        fun calculationsStarted(value: Number, operationType: String) {

        }
    }
}