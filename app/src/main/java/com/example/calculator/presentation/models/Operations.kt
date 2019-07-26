package com.example.calculator.presentation.models

import com.example.calculator.MainApplication
import com.example.calculator.R
import java.math.BigDecimal
import kotlin.Double.Companion.NaN

interface Action {
    /**
     * возвращает результат вычислений
     * @param leftVal левый операнд
     * @param rightVal правый операнд
     */
    fun action(leftVal: BigDecimal, rightVal: BigDecimal): BigDecimal?
}

interface Sign {
    /**
     * возвращает знак оерации
     */
    fun getSign(): String
}

enum class Operations(operation: String) : Action,
    Sign {
    MULTIPLY(MainApplication.instance.getRsources().getString(R.string.operator_multiply)) {
        override fun getSign(): String {
            return "x "
        }

        override fun action(leftVal: BigDecimal, rightVal: BigDecimal): BigDecimal? {
            return leftVal * rightVal
        }
    },
    ADD(MainApplication.instance.getRsources().getString(R.string.operator_add)) {
        override fun getSign(): String {
            return "+ "
        }

        override fun action(leftVal: BigDecimal, rightVal: BigDecimal): BigDecimal? {
            return leftVal + rightVal
        }
    },
    SUBTRACT(MainApplication.instance.getRsources().getString(R.string.operator_subtract)) {
        override fun getSign(): String {
            return "- "
        }

        override fun action(leftVal: BigDecimal, rightVal: BigDecimal): BigDecimal? {
            return leftVal - rightVal
        }
    },
    DIVISION(MainApplication.instance.getRsources().getString(R.string.operator_division)) {
        override fun getSign(): String {
            return "% "
        }

        override fun action(leftVal: BigDecimal, rightVal: BigDecimal): BigDecimal? {
            return if (rightVal == BigDecimal(0))
                BigDecimal(NaN)
            else
                leftVal / rightVal

        }
    },
    EQUAL(MainApplication.instance.getRsources().getString(R.string.operator_equal)) {
        override fun getSign(): String {
            return "= "
        }

        override fun action(leftVal: BigDecimal, rightVal: BigDecimal): BigDecimal? {
            return BigDecimal(0)
        }
    };

    companion object {
        fun create(operation: String): Operations {

            return when (operation) {
                MainApplication.instance.getRsources().getString(R.string.operator_multiply) -> MULTIPLY
                MainApplication.instance.getRsources().getString(R.string.operator_division) -> DIVISION
                MainApplication.instance.getRsources().getString(R.string.operator_subtract) -> SUBTRACT
                MainApplication.instance.getRsources().getString(R.string.operator_add) -> ADD
                MainApplication.instance.getRsources().getString(R.string.operator_equal) -> EQUAL
                else -> null
            } as Operations
        }
    }
}