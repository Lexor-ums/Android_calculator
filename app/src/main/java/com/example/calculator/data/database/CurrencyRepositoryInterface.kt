package com.example.calculator.data.database

interface CurrencyRepositoryInterface {
    fun getAll() : List<CurrencyUnit>
    fun insertCurrency(currencyUnit: CurrencyUnit)
}