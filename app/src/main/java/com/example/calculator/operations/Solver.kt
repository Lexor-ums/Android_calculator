package com.example.calculator.operations

/**
 * Класс, хранящий последнюю запрошенную операцию и последний результат
 */
class Solver {
    private var leftValue: Number? = null
    private var lastOperator: Operator? = null

    /**
     * добавление операции в "очередь"
     * @param operator - тип выполняемой операции
     * @param value - правый операнд выражения
     * @return полученное значение, если возможно
     */
    fun addOperator(operator: Operator, value: Number): Number? {
        return if (leftValue != null && lastOperator != null) {
            val left = leftValue
            leftValue = lastOperator?.calc(left!!, value)
            println("result $leftValue")
            lastOperator = operator
            leftValue
        } else {
            lastOperator = operator
            leftValue = value
            null
        }

    }

    /**
     * завершение расчётов и возврат конечного результата
     * @return конечное значение
     */
    fun finish(value : Number) : Number?   {
        if(leftValue == null || value == null)
            return null
        val left = leftValue
        val operator = lastOperator
        leftValue = null
        lastOperator = null
        return operator?.calc(left!!, value)
    }
}