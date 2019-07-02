package com.example.calculator.operations

import java.lang.Double.NaN

sealed class Operator{
    /**
     * абстрактный метод, возвращающий результат операции над числами
     * @param leftVal левый операнд
     * @param rightVal правый операнд
     */
    abstract fun calc(leftVal : Number, rightVal : Number) : Number

    /**
     * Класс, предотавляющий операцию сложения
     */
    object Addition : Operator() {
        override fun calc(leftVal: Number, rightVal: Number): Number {
            return when (leftVal) {
                is Int -> when (rightVal) {
                    is Int -> leftVal + rightVal
                    is Double -> leftVal + rightVal
                    else -> 0
                }
                is Double -> when (rightVal) {
                    is Int -> leftVal + rightVal
                    is Double -> leftVal + rightVal
                    else -> 0
                }
                else -> 0
            }
        }
    }
    /**
     * Класс, предотавляющий операцию вычитания
     */
    object Subtraction : Operator() {
        override fun calc(leftVal: Number, rightVal: Number): Number {
            return when (leftVal) {
                is Int -> when (rightVal) {
                    is Int -> leftVal - rightVal
                    is Double -> leftVal - rightVal
                    else -> 0
                }
                is Double -> when (rightVal) {
                    is Int -> leftVal - rightVal
                    is Double -> leftVal - rightVal
                    else -> 0
                }
                else -> 0
            }
        }
    }

    /**
     * Класс, предотавляющий операцию деления
     */
    object Division : Operator() {
        override fun calc(leftVal: Number, rightVal: Number): Number {
            if (rightVal.toString() == "0")
                return NaN
            return when (leftVal) {
                is Int -> when (rightVal) {
                    is Int -> leftVal / rightVal
                    is Double -> leftVal / rightVal
                    else -> 0
                }
                is Double -> when (rightVal) {
                    is Int -> leftVal / rightVal
                    is Double -> leftVal / rightVal
                    else -> 0
                }
                else -> 0
            }
        }
    }

    /**
     * Класс, предотавляющий операцию умножения
     */
    object Multiply : Operator() {
        override fun calc(leftVal: Number, rightVal: Number): Number {
            return when (leftVal) {
                is Int -> when (rightVal) {
                    is Int -> leftVal * rightVal
                    is Double -> leftVal * rightVal
                    else -> 0
                }
                is Double -> when (rightVal) {
                    is Int -> leftVal * rightVal
                    is Double -> leftVal * rightVal
                    else -> 0
                }
                else -> 0
            }
        }
    }
}