package com.example.calculator.domain.interactors

import com.example.calculator.data.net.retrofit.CurRateApiService
import com.example.calculator.utils.API_KEY
import com.example.calculator.utils.CurrencyPairGenerator
import com.example.calculator.utils.events.Events
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

import javax.inject.Inject

class LoadCurrencyInteractor @Inject constructor(): BaseInteractor(){
    @Inject
    lateinit var api : CurRateApiService

    fun requestCurrency() {
        GlobalScope.launch {
            val currency = safeApiCall(
                call = { api.getActualAsync(CurrencyPairGenerator.requestPairStr, API_KEY).await() },
                errorMessage = "Can`t do request fon network"
            )
            EventBus.getDefault().post(Events.CurrencyLoaded(currency?.data))
        }
    }
}