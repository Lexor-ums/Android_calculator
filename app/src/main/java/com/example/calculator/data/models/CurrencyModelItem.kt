package com.example.calculator.data.models

import androidx.lifecycle.MutableLiveData

data class CurrencyModelItem(
    val source: MutableLiveData<String>,
    val price: MutableLiveData<String>,
    val target: MutableLiveData<String>
)