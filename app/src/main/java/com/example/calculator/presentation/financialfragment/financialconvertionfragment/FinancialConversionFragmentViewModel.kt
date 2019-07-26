package com.example.calculator.presentation.financialfragment.financialconvertionfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.calculator.MainApplication
import com.example.calculator.R
import com.example.calculator.data.repository.CurrencyRepositoryImpl
import com.example.calculator.data.database.entity.CurrencyUnit
import javax.inject.Inject

class FinancialConversionFragmentViewModel @Inject constructor(repository: CurrencyRepositoryImpl) : ViewModel() {
    private var currencyList: List<CurrencyUnit> = repository.getAll()
    var currentTarget = -1
    var currentSource = -1
    var sourceCash = MutableLiveData("1")
    var targetCash = MutableLiveData("")
    var targetCashChangeEvent = Transformations.map(sourceCash) {
        calcCoast()
    }


    private var array = MainApplication.instance.getRsources().getStringArray(R.array.money_symbols)

    fun calcCoast() {
        if (currentSource == -1 || currentTarget == -1 || sourceCash.value!!.isEmpty())
            return
        var sourcePrice = array[currentSource]
        var targetPrice = array[currentTarget]
        var coastTarget = 0.0
        var coastSource = 0.0
        var rub = MainApplication.instance.getRsources().getString(R.string.rub)
        for (it in currencyList) {
            if (it.name == sourcePrice) {
                coastSource = it.price.toDouble()
            }
        }
        for (it in currencyList) {
            if (it.name == targetPrice) {
                coastTarget = it.price.toDouble()
            }
        }
        if (sourcePrice == rub)
            coastSource = 1.0
        if (targetPrice == rub)
            coastTarget = 1.0
        var exchangeRate = coastSource / coastTarget
        targetCash.value = (sourceCash.value?.toDouble()?.times(exchangeRate)).toString()
    }

    fun getPrice(sourceIndex: Int = -1, targetIndex: Int = -1) {
        if (sourceIndex != -1)
            currentSource = sourceIndex
        if (targetIndex != -1)
            currentTarget = targetIndex
        calcCoast()
    }
}