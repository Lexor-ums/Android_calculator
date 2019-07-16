package com.example.calculator.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory<T>(private val v: T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(v!!::class.java))
            return v as T
        throw IllegalArgumentException("Unknown class name")
    }

}