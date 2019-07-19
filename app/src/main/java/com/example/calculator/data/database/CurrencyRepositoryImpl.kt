package com.example.calculator.data.database

import android.os.AsyncTask
import javax.inject.Inject


class CurrencyRepositoryImpl @Inject  constructor(private val dao: CurrencyDao) : CurrencyRepositoryInterface {
    override fun getAll(): List<CurrencyUnit> {
        class GetAllData : AsyncTask<Void, Void, List<CurrencyUnit>>(){
            override fun doInBackground(vararg params: Void?): List<CurrencyUnit> {
                return dao.getAll()
            }
        }
        return GetAllData().execute().get()
    }

    override fun insertCurrency(currencyUnit: CurrencyUnit) {
        class InsertData : AsyncTask<Void, Void, Void?>(){
            override fun doInBackground(vararg params: Void?): Void? {
                dao.insertCurrency(currencyUnit)
                return null
            }
        }
        InsertData().execute()
    }

}

