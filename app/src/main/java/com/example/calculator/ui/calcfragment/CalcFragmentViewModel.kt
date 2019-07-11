package com.example.calculator.ui.calcfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

class CalcFragmentViewModel : ViewModel() {
    private val __valueToInput = MutableLiveData("0")
    val valueToInput: LiveData<String> = __valueToInput

    private var prevValue: Number = 0
    private var currentValue: Number = 0

    private var isDecimal = false

    fun onDigitClick(digit: Int) {
        try {
            val newValue: Number = when (isDecimal) {
                true -> {
                    if (!currentValue.toString().contains('.'))
                        ("$currentValue.$digit").toDouble()
                    else
                        (currentValue.toString() + digit.toString()).toDouble()
                }
                else -> (currentValue.toString() + digit.toString()).toLong()
            }
            currentValue = newValue
            __valueToInput.value = newValue.toString()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun onDotClick() {
        if (isDecimal)
            return
        isDecimal = true
        __valueToInput.value + __valueToInput.value + "."
    }

    fun onEraseClick() {
        var newValue = __valueToInput.value
        if (newValue == null || newValue.isEmpty()) {
            return
        }
        println("$newValue")
        if (newValue!!.last() == '.') {
            newValue = newValue.dropLast(1)
            isDecimal = false
            __valueToInput.value = newValue
            currentValue = newValue.toLong()
        } else {
            __valueToInput.value = newValue.dropLast(1)
            if(__valueToInput.value!!.isEmpty()){
                __valueToInput.value = "0"
                currentValue = 0
                return
            }
            currentValue = if (__valueToInput.value?.last() == '.') {
                newValue.dropLast(2).toLong()
            } else
                newValue.dropLast(1).toDouble()
        }
    }
}