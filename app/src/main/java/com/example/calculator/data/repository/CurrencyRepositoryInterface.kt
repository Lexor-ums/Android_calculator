package com.example.calculator.data.repository

import com.example.calculator.data.database.entity.CurrencyUnit

interface CurrencyRepositoryInterface {
    fun getAll() : List<CurrencyUnit>
    fun insertCurrency(currencyUnit: CurrencyUnit)
}