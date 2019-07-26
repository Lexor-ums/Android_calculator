package com.example.calculator.presentation.financialfragment.financialexchangefragment

import androidx.lifecycle.ViewModel
import com.example.calculator.data.net.retrofit.CurRateApiService
import com.example.calculator.data.repository.CurrencyRepositoryImpl
import com.example.calculator.data.database.entity.CurrencyUnit
import com.example.calculator.domain.interactors.LoadCurrencyInteractor
import com.example.calculator.utils.CurrencyPairGenerator
import com.example.calculator.utils.events.Events
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class FinancialExchangeFragmentViewModel @Inject constructor() : ViewModel() {

    private val pairsList = CurrencyPairGenerator.generatePairs()
    private val currencyAdapter = CurrencyItemViewAdapter(pairsList)
    @Inject
    lateinit var api: CurRateApiService

    @Inject
    lateinit var interactor : LoadCurrencyInteractor
    @Inject
    lateinit var repository: CurrencyRepositoryImpl

    fun getAdapter() : CurrencyItemViewAdapter{
        return currencyAdapter
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onCurrencyLoaded(event: Events.CurrencyLoaded){
        println("subscribe ${event.pairs}")
        parseRequest(event.pairs)
    }

    fun onMoneyRequestClick() {
        interactor.requestCurrency()
    }

    fun parseRequest(pairs: Map<String, String>?) {
        if (pairs != null) {
            for ((k, v) in pairs) {
                updatePairsList(k ,v)
            }
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
            repository.insertCurrency(
                CurrencyUnit(
                    currency.source.value!!,
                    currency.price.value!!
                )
            )
        }
    }

    fun restoreData(){
        for( currency in repository.getAll()){
            updatePairsList(currency.name, currency.price)
        }
    }

    fun onStart(){
        EventBus.getDefault().register(this)
    }

    fun onStop(){
        EventBus.getDefault().unregister(this)
    }
}