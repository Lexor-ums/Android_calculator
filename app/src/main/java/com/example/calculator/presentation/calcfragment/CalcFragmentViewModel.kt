package com.example.calculator.presentation.calcfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.presentation.models.HistoryModelItem
import com.example.calculator.presentation.models.Operations
import java.lang.Exception
import java.math.BigDecimal
import javax.inject.Inject

class CalcFragmentViewModel @Inject constructor() : ViewModel() {
    private var recyclerView: RecyclerView? = null

    private val __valueToInput = MutableLiveData("0")
    private val __operationSign = MutableLiveData("")
    private val __result = MutableLiveData("")
    private val __isComplite = MutableLiveData(false)
    private val historyAdapter: HistoryViewAdapter = HistoryViewAdapter(mutableListOf())
    var count = -1

    val valueToInput: LiveData<String> = __valueToInput
    val operationSign = __operationSign
    val result: LiveData<String> = __result
    val isComplite = __isComplite

    private var prevValue: BigDecimal = BigDecimal.valueOf(0)
    private var prevBuffValue: BigDecimal = BigDecimal.valueOf(0)
    private var currentValue: BigDecimal = BigDecimal.valueOf(0)
    private var resultValue: BigDecimal = BigDecimal.valueOf(0)
    private var lastOperation: Operations? = null

    private var isDecimal = false

    /**
     * установка Recycler view
     * @param view - id view
     */

    fun setRecylcer(view: RecyclerView) {
        this.recyclerView = view
    }

    /**
     * возвращает ссылку на адаптер
     */
    fun getAdapter(): HistoryViewAdapter {
        return historyAdapter
    }

    /**
     * обработка ввода цифр
     * @param  digit - введенная цифра
     */

    fun onDigitClick(digit: Int) {
        try {
            val newValue: BigDecimal = when (isDecimal) {
                true -> {
                    if (!currentValue.toString().contains('.'))
                        ("$currentValue.$digit").toBigDecimal()
                    else
                        (currentValue.toString() + digit.toString()).toBigDecimal()
                }
                else -> (currentValue.toString() + digit.toString()).toBigDecimal()
            }
            currentValue = newValue
            __valueToInput.value = newValue.toString()
        } catch (e: Exception) {
            println(e.message)
        }
        if (lastOperation != null) {
//            resultValue = prevValue
            resultValue = lastOperation?.action(prevValue, currentValue)!!
            __result.value = "= $resultValue"
        }
    }

    /**
     * обработка ввода чисел с плавающей точкой
     */
    fun onDotClick() {
        if (isDecimal)
            return
        isDecimal = true
        __valueToInput.value += "."
    }

    /**
     * обработка события коррекции введённого числа
     */
    fun onEraseClick() {
        val tmp = currentValue
        var newValue = __valueToInput.value
        if (newValue == null || newValue.isEmpty()) {
            return
        }
        if (newValue.last() == '.') {
            newValue = newValue.dropLast(1)
            isDecimal = false
            __valueToInput.value = newValue
            currentValue = newValue.toBigDecimal()
        } else {
            __valueToInput.value = newValue.dropLast(1)
            if (__valueToInput.value!!.isEmpty()) {
                __valueToInput.value = "0"
                currentValue = BigDecimal.valueOf(0)
                return
            }
            currentValue = when {
                __valueToInput.value?.last() == '.' -> newValue.dropLast(2).toBigDecimal()
                isDecimal -> newValue.dropLast(1).toBigDecimal()
                else -> newValue.dropLast(1).toBigDecimal()
            }
        }
        resultValue -= (tmp - currentValue)
        __result.value = "= $resultValue"
    }

    /**
     * обработка выбора арифметической операции
     * @param operation - символ операции
     */
    fun onOperatorClicked(operation: String) {
        prevBuffValue = prevValue
        prevValue = if (lastOperation != null)
            resultValue
        else
            currentValue
        lastOperation = Operations.create(operation)
        isDecimal = false
        __valueToInput.value = ""
        __operationSign.value = lastOperation?.getSign()
        ++count
        updateHistory()
    }

    /**
     * очистка событий
     */
    fun onClearClick() {
        __result.value = ""
        __valueToInput.value = "0"
        __operationSign.value = ""
        currentValue = BigDecimal.valueOf(0)
        prevValue = BigDecimal.valueOf(0)
        resultValue = BigDecimal.valueOf(0)
        lastOperation = null
        historyAdapter.clear()
    }

    /**
     * завершение вычислений
     */
    fun onEqualClick(operation: String) {
        __isComplite.value = true
        __result.value = ""
        resultValue = prevValue
        resultValue = lastOperation?.action(resultValue, currentValue)!!
        __valueToInput.value = "$resultValue"
        lastOperation = Operations.create(operation)
        __operationSign.value = lastOperation?.getSign()
        currentValue = BigDecimal.valueOf(0)
        prevValue = BigDecimal.valueOf(0)
        resultValue = BigDecimal.valueOf(0)
        lastOperation = null
    }

    /**
     * добавление новой записи в историю операций
     */
    private fun updateHistory() {
        historyAdapter.addItem(
            HistoryModelItem(
                prevValue,
                currentValue,
                lastOperation!!.getSign(),
                prevBuffValue
            )
        )
        recyclerView!!.smoothScrollToPosition(count)
        currentValue = BigDecimal.valueOf(0)
    }
}