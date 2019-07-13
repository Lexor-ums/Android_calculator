package com.example.calculator.ui.calcfragment

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.MainApplication
import com.example.calculator.R
import com.example.calculator.operations.Operations
import java.lang.Exception
import java.math.BigDecimal

class CalcFragmentViewModel : ViewModel() {
    private val __valueToInput = MutableLiveData("0")
    private val __operationSign = MutableLiveData("")
    private val __result = MutableLiveData("")

    val valueToInput: LiveData<String> = __valueToInput
    val operationSign = __operationSign
    val result : LiveData<String> = __result

    private var prevValue: BigDecimal = BigDecimal.valueOf(0)
    private var currentValue: BigDecimal = BigDecimal.valueOf(0)
    private var resultValue  :BigDecimal = BigDecimal.valueOf(0)
    private var lastOperation: Operations? = null

    private var isDecimal = false

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
        if(lastOperation != null){
            resultValue = prevValue
            resultValue = lastOperation?.action(resultValue, currentValue)!!
            println("result $resultValue $currentValue $prevValue")
            __result.value = "= $resultValue"
        }
    }

    fun onDotClick() {
        if (isDecimal)
            return
        isDecimal = true
        __valueToInput.value += "."
    }

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

    fun onOperatorClicked(operation: String) {
        prevValue = if (lastOperation != null)
            resultValue
        else
            currentValue
        lastOperation = Operations.create(operation)
        currentValue = BigDecimal.valueOf(0)
        isDecimal = false
        __valueToInput.value = ""
        __operationSign.value = lastOperation?.getSign()
    }
}