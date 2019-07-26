package com.example.calculator.presentation.financialfragment.financialexchangefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.presentation.models.CurrencyModelItem

class CurrencyItemViewModel(private val item: CurrencyModelItem) : ViewModel() {
    var sourceCurrency = MutableLiveData("")
    var targetCurrency = MutableLiveData("")
    var currencyPrice = MutableLiveData("")

    init {
        sourceCurrency = item.source
        targetCurrency = item.target
        currencyPrice = item.price
    }


}