package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculator.operations.Operator
import com.example.calculator.operations.Solver
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var historyTextView: TextView
    private var solver = Solver()
    private var isNewNumber : Boolean = true
    private var isFloatingPointNumber : Boolean = false
    private var isSolved : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = dataField
        historyTextView = historyField
        historyTextView.movementMethod = ScrollingMovementMethod()
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
            textView.text = ""
            isSolved = false
        }
        val button : Button? = view as? Button
        var digit: String = button?.text.toString()
        var number: String = when(isNewNumber){
            true -> digit
            else -> textView.text.toString() + digit
        }
        try {
            if(!isFloatingPointNumber)
                textView.text = number.toLong().toString()
            else
                textView.text = number.toDouble().toString()
        }
        catch (e: NumberFormatException){
            Toast.makeText(this, "number to big", "number to big".length)
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
            var number: String = textView.text.toString() + "."
            textView.text = number
            isFloatingPointNumber = true
        }

    }

    /**
     * обработка события сброса данных
     */
    fun onClear(@Suppress("UNUSED_PARAMETER")view: View){
        textView.text = "0"
        historyTextView.text = ""
        isFloatingPointNumber = false
        isNewNumber = true
    }

    /**
     * обработка события удаления введённого символа
     */
    fun onEraseClicked(@Suppress("UNUSED_PARAMETER")view: View){
        if (textView.text.isEmpty())
            return
        var number : String = textView.text.toString()
        if(number.get(number.length - 1).equals('.')) {
            isFloatingPointNumber = false

        }
        number = number.take(number.length - 1)
        textView.text = number
    }

    /**
     * обработка выбора операций над числами
     */
    fun onOperatorClicked(view: View) {
        var number: Number?
        val button : Button? = view as? Button
        try {
            if(isFloatingPointNumber)
                number = textView.text.toString().toDouble()
            else
                number = textView.text.toString().toLong()
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
                    textView.text = "= $res"
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
            textView.text = "0"
            isNewNumber = true
            isFloatingPointNumber = false
        }
        else{
            historyTextView.append("\n" + number.toString())
            historyTextView.append("\n" + button?.text.toString())
            textView.text = "0"
            isNewNumber = true
            isFloatingPointNumber = false
        }

    }
}
