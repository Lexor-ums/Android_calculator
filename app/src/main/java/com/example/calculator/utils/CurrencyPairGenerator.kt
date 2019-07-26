package com.example.calculator.utils

import androidx.lifecycle.MutableLiveData
import com.example.calculator.MainApplication
import com.example.calculator.R
import com.example.calculator.presentation.models.CurrencyModelItem

class CurrencyPairGenerator {
    companion object{
        var requestPairStr = ""
        fun generatePairs() : MutableList<CurrencyModelItem>{
            val list = mutableListOf <CurrencyModelItem>()
            val currencyResourceArray = MainApplication.instance.getRsources().getStringArray(R.array.money_symbols)
            val rubStr = MainApplication.instance.getRsources().getString(R.string.rub)
            for(currency in currencyResourceArray){
                if(!currency.contains(rubStr)) run {
                    val source = MutableLiveData(currency)
                    val target = MutableLiveData(rubStr)
                    val price = MutableLiveData("0")
                    var item =
                        CurrencyModelItem(
                            source,
                            price,
                            target
                        )
                    list.add(item)
                    requestPairStr += "$currency$rubStr,"
                }
            }
            requestPairStr = requestPairStr.dropLast(1)
            return list
        }
    }
}