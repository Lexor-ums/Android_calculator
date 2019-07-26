package com.example.calculator.utils.events

sealed class Events {
    class CurrencyLoaded(val pairs : Map<String, String>?) : Events()
}