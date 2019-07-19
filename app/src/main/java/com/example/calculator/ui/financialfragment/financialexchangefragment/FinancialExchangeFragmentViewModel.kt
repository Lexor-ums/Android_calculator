package com.example.calculator.ui.financialfragment.financialexchangefragment

import androidx.databinding.Bindable
import androidx.databinding.InverseMethod
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.api.CurRateApiService
import com.example.calculator.data.dao.Response
import com.example.calculator.data.database.CurrencyRepositoryImpl
import com.example.calculator.data.database.CurrencyUnit
import com.example.calculator.utils.API_KEY
import com.example.calculator.utils.CurrencyPairGenerator
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class FinancialExchangeFragmentViewModel @Inject constructor() : ViewModel() {

    private val pairsList = CurrencyPairGenerator.generatePairs()
    private val currencyAdapter = CurrencyItemViewAdapter(pairsList)
    @Inject
    lateinit var api: CurRateApiService

    @Inject
    lateinit var repository: CurrencyRepositoryImpl

    fun getAdapter() : CurrencyItemViewAdapter{
        return currencyAdapter
    }
    fun onMoneyRequestClick() {
        val moneys = api.getActual(CurrencyPairGenerator.requestPairStr, API_KEY)
        moneys.enqueue(object : Callback<com.example.calculator.data.dao.Response> {
            override fun onResponse(
                call: Call<com.example.calculator.data.dao.Response>,
                response: retrofit2.Response<Response>
            ) {
                println("Result " + response.body()?.data.toString())
                response.body()?.data?.let { parseRequest(it) }
                currencyAdapter.updateData()
            }

            override fun onFailure(call: Call<com.example.calculator.data.dao.Response>, t: Throwable) {
                println("Display error code  ${t.toString()}")
            }
        })
    }

    fun parseRequest(pairs: Map<String, String>) {
        for ((k, v) in pairs) {
            updatePairsList(k ,v)
        }
    }

    private fun updatePairsList(key: String, value : String){
        for(pair in pairsList){
            if(key.contains(pair.source.value!!)){
                pair.price.value = value
            }
        }
    }

    fun storeData() {
        for ( currency in pairsList){
            repository.insertCurrency(CurrencyUnit(currency.source.value!!, currency.price.value!!))
        }
    }

    fun restoreData(){
        for( currency in repository.getAll()){
            updatePairsList(currency.name, currency.price)
        }
    }
}