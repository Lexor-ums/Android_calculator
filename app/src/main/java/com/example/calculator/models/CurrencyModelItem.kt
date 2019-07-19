package com.example.calculator.models

import android.R
import androidx.lifecycle.MutableLiveData

data class CurrencyModelItem(
    val source: MutableLiveData<String>,
    val price: MutableLiveData<String>,
    val target: MutableLiveData<String>
)